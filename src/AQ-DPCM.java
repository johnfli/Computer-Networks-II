	/* 6. Receive AQ-DPCM Sound Clip Function */
	public static void soundAQDPCM( int audioClipNumber ) throws IOException, LineUnavailableException {

		String soundCodeAQDPCM = soundCode;

		soundCodeAQDPCM += SelectSong( );

		soundCodeAQDPCM += "AQF999";

		System.out.printf(
		  		  " __________________________________________________________________\n"
				+ " |                                                                |\n"
				+ " | Receiving AQ-DPCM Sound Clip %d.....                            |\n"	
				+ " |                                                                |\n", audioClipNumber );

		int i 			 = 0;
		int count 		 = 0;
		int bpp 		 = 132;
		int soundPackets = 999;
		int samplesSize  = 2 * bpp * soundPackets;
		int lsb 		 = 0;
		int msb 		 = 0;

		int[] mean 			 = new int[soundPackets];
		int[] step 			 = new int[soundPackets];
		int[] soundDiffs 	 = new int[samplesSize];
		int[] soundSamples 	 = new int[samplesSize];
		byte[] receivedSound = new byte[bpp * soundPackets];

		byte[] txbuffer, rxbuffer;
		
		String folder 				= "../out/6. AQ-DPCM Sound/";
		String samplesFileName 		= "SamplesAQDPCM" + String.valueOf( audioClipNumber ) + ".txt";
		String samplesDiffsFileName = "SamplesDiffsAQDPCM" + String.valueOf( audioClipNumber ) + ".txt";
		String meansFileName		= "means" + String.valueOf( audioClipNumber ) + ".txt";
		String stepsFileName		= "steps" + String.valueOf( audioClipNumber ) + ".txt";

		PrintWriter samples 		= new PrintWriter( folder + samplesFileName );
		PrintWriter samplesDiffs 	= new PrintWriter( folder + samplesDiffsFileName );
		PrintWriter means 			= new PrintWriter( folder + meansFileName );
		PrintWriter steps 			= new PrintWriter( folder + stepsFileName );
		
		DatagramSocket s = new DatagramSocket();
		
		txbuffer = soundCodeAQDPCM.getBytes();

		DatagramPacket p = new DatagramPacket( txbuffer, txbuffer.length, hostAddress, serverPort );
		
		DatagramSocket r = new DatagramSocket( clientPort );
		
		r.setSoTimeout( 1000 );

		rxbuffer = new byte[bpp];
		
		DatagramPacket q = new DatagramPacket( rxbuffer, rxbuffer.length );
		
		s.send( p );
		
		for( ;; ) {

			try {

				r.receive( q );
				for(int j=0; j<bpp; j++)
					receivedSound[i*bpp + j] = rxbuffer[j];
				i++;

			} catch ( Exception x ) {

				System.out.println( x );
				break;
			}
		}
		
		System.out.printf(
				  " |                                                                |\n"
				+ " | Finished.... Number of AQ-DPCM Sound Packets Received: %d     |\n"
				+ " |----------------------------------------------------------------|\n"
				+ " |                                                                |\n"
				+ " | Sound Clip %d Processing....                                    |\n"
				+ " |                                                                |\n", i, audioClipNumber );

		AudioFormat linearPCM = new AudioFormat( 8000, 16, 1, true, false );
		SourceDataLine lineOut = AudioSystem.getSourceDataLine( linearPCM );
		lineOut.open( linearPCM, 2 * 2 * 128 * soundPackets ); 
		
		for (i = 0; i < soundPackets; i++) {   
			lsb 	= (int) receivedSound[bpp*i];
			msb 	= (int) receivedSound[bpp*i + 1];
			mean[i] = (256 * msb) + (lsb & 0x00FF);
			lsb 	= (int) receivedSound[bpp*i + 2];
			msb 	= (int) receivedSound[bpp*i + 3];
			step[i] = (256 * (msb & 0x00FF)) + (lsb & 0x00FF);
		}
		
		for(i = 0; i < soundPackets; i++) {
			means.println( mean[i] );
			steps.println( step[i] );
		}
		
		for (i = 0; i < soundPackets; i++) {
			for(int j = 4; j < bpp; j++) {
				int k = (int) receivedSound[i*bpp + j];
				soundDiffs[2*count + 1] = ((k & 0x0F) - 8);
				soundDiffs[2*count] 	= (((k >> 4) & 0x0F) - 8);
				count++;
			}
		}
		
		byte[] audioBufferOut = new byte[2 * 2 * 128 * soundPackets]; //512 * soundPackets = 128*2*soundPackets*2
			
		soundSamples[0]=0;
		for(i = 1; i < soundPackets; i++)
			for(int j = 0; j < 256; j++)
				soundSamples[i*256 + j] = step[i]/2 * soundDiffs[i*256 + j] + soundSamples[i*256 + j - 1];	// step[i] *
			
		for(i = 0; i < 256 * soundPackets; i++){
			audioBufferOut[2*i] 	= (byte) ( soundSamples[i] & 0x00FF );
			audioBufferOut[2*i + 1] = (byte) ((soundSamples[i] >> 8) & 0x00FF);
		}
		
		for(i = 0; i < 256 * soundPackets; i++) {
			samples.println( audioBufferOut[2*i] );
			samples.println( audioBufferOut[2*i + 1] );
			samplesDiffs.println( soundDiffs[i] );
		}


		System.out.printf(
				  " |                                                                |\n"
				+ " | Finished Processing....                                        |\n"
				+ " |----------------------------------------------------------------|\n"
				+ " |                                                                |\n"
				+ " | Playing Sound Clip %d....                                       |\n"
				+ " |                                                                |\n", audioClipNumber );
				
		lineOut.start();
		lineOut.write( audioBufferOut, 0, audioBufferOut.length );
		lineOut.drain();
		
		System.out.println(
				  " | Finished....                                                   |\n"
		  		+ " |________________________________________________________________|\n" );		

		lineOut.close();
		s.close();
		r.close();
		samples.close();
		samplesDiffs.close();
		means.close();
		steps.close();	
	}
