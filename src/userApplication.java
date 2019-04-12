/************************************************************************************
 *
 * <Computer Networks II>
 *
 * <userApplication.java> -- The java implementation of the Computer Networks II 
 *                   	   	 Experimental Virtual Lab Project, file ergasia2.pdf 
 *
 *
 * <NAME: Flionis Ioannis> 
 * <AEM: 8263> 
 * <CONTACT: iflionis@auth.gr>
 *
 ************************************************************************************/

import java.net.*;
import java.util.Scanner;
import java.io.*;
import javax.sound.sampled.*;

public class userApplication {
	
	private static int portNumber			= 25;
	private static int clientPort 			= 48000 + portNumber;
	private static int serverPort 			= 38000 + portNumber;
	private static String echoCode 			= "E3593";
	private static String imageCode 		= "M9498";
	private static String soundCode 		= "M9498"; 
	private static String ithakiCopterCode 	= "Q3760";
	private static String obdVehicleCode 	= "V0975";
	private static byte[] hostIP 			= { (byte) 155, (byte) 207, 18, (byte) 208 };
	private static InetAddress hostAddress;
	private static Scanner input;

	public static void main( String[] args ) throws IOException, LineUnavailableException {
	
		hostAddress = InetAddress.getByAddress( hostIP );

		for ( ;; ) {

			System.out.println(
					  " __________________________________________________________________\n"
					+ " |                                                                |\n"
					+ " |  A P P L I C A T I O N   O P T I O N S   T A B L E :           |\n"
					+ " |________________________________________________________________|\n"
					+ " |                                                                |\n"
					+ " | 1. Echo Packet Transmission.                                   |\n"
					+ " | 2. Temperature Packet Transmission.                            |\n"
					+ " | 3. Camera Image Packet Transmission.                           |\n"
					+ " | 4. Frequency Generator Packet Transmission.                    |\n"
					+ " | 5. DPCM Sound Packet Transmission.                             |\n"
					+ " | 6. AQDPCM Sound Packet Transmission.                           |\n"
					+ " | 7. IthakiCopter Telemetry Packet Transmission.                 |\n"
					+ " | 8. OnBoard Car Fault Diagnostics (OBD-II) Packet Transmission. |\n"
					+ " | 0. EXIT PROGRAM                                                |\n"
					+ " |________________________________________________________________|\n"
					+ "\n Select one of the available options by pressing the mapped key: " 	);
			
			input = new Scanner( System.in );
			int pressedKey = input.nextInt();
			
			switch ( pressedKey ) {

				/* 1. Echo Packet Transmission */
				case 1: {
					
					for ( ;; ) {
						
						System.out.println(
						  		  " __________________________________________________________________\n"
								+ " |                                                                |\n"
								+ " |  E C H O   P A C K E T   O P T I O N S :                       |\n"
								+ " |________________________________________________________________|\n"
								+ " |                                                                |\n"
								+ " |  0. For Echo Packet with no added delay                        |\n"
								+ " |  1. For Echo Packet with random delay                          |\n"
								+ " |________________________________________________________________|\n"
								+ "\n Select one of the available options by pressing the mapped key: " 	);

						input = new Scanner( System.in );
						pressedKey = input.nextInt();
						
						if ( (pressedKey != 1) && (pressedKey != 0) ) {
							
							System.out.println(
							  		  " __________________________________________________________________\n"
									+ " |                                                                |\n"
									+ " |  Please choose one of the available options [0-1]              |\n"
									+ " |  ( Type in only the equivalent number )                        |\n"
							  		+ " |________________________________________________________________|\n" );		
						} else {

							break;
						}
					}

				    echoPacket( pressedKey );
			
				    break;
				}
				/* 2. Temperature Packet Transmission */
				case 2: {
					
					echoPacketTemperature( );
					break;
				}
				/* 3. Camera Image Packet Transmission */
				case 3: {

					for ( ;; ) {
						
						System.out.println(
						  		  " __________________________________________________________________\n"
								+ " |                                                                |\n"
								+ " |  C A M E R A   C O N F I G U R A T I O N   O P T I O N S :     |\n"
						  		+ " |________________________________________________________________|\n"
								+ " |                                                                |\n"
								+ " |  0. For Configuration CAM = FIX                                |\n"
								+ " |  1. For Configuration CAM = PTZ                                |\n"
								+ " |________________________________________________________________|\n"
								+ "\n Select one of the available options by pressing the mapped key: "		);

						input = new Scanner( System.in );
						pressedKey = input.nextInt();

						if ( (pressedKey != 1) && (pressedKey != 0) ) {
							
							System.out.println(
							  		  " __________________________________________________________________\n"
									+ " |                                                                |\n"
									+ " |  Please choose one of the available options [0-1]              |\n"
									+ " |  ( Type in only the equivalent number )                        |\n"
							  		+ " |________________________________________________________________|\n" );		
						} else {

							break;
						}
					}

					if ( pressedKey == 1 ) {

						for ( ;; ) {
							
							System.out.println(
							  		  " __________________________________________________________________\n"
									+ " |                                                                |\n"
									+ " |  C A M E R A   D I R E C T I O N   O P T I O N S :             |\n"
							  		+ " |________________________________________________________________|\n"
									+ " |                                                                |\n"
									+ " |  0. For Configuration DIR = ' '                                |\n"
									+ " |  1. For Configuration DIR = 'L'                                |\n"
									+ " |  2. For Configuration DIR = 'U'                                |\n"
									+ " |  3. For Configuration DIR = 'R'                                |\n"
									+ " |  4. For Configuration DIR = 'D'                                |\n"
									+ " |  5. For Configuration DIR = 'M'                                |\n"
									+ " |  6. For Configuration DIR = 'C'                                |\n"
									+ " |________________________________________________________________|\n"
									+ "\n Select one of the available options by pressing the mapped key: "		);

							input = new Scanner( System.in );
							pressedKey += input.nextInt();

							if ( (pressedKey < 1) || (pressedKey > 7) ) {
								
								System.out.println(
								  		  " __________________________________________________________________\n"
										+ " |                                                                |\n"
										+ " |  Please choose one of the available options [0-6]              |\n"
										+ " |  ( Type in only the equivalent number )                        |\n"
								  		+ " |________________________________________________________________|\n" );		
							} else {

								break;
							}
						}						
					}

					imageReceive( pressedKey );

				    break;
				}
				/* 4. Virtual Frequency Generator Packet Transmission */
				case 4: {

					soundDPCM( 0 );
					break;
				}				
				/* 5. DPCM Sound Packet Transmission */
				case 5: {

					soundDPCM( 1 );
					break;
				}
				/* 6. AQDPCM Sound Packet Transmission */
				case 6: {

					soundAQDPCM( 1 );
					soundAQDPCM( 2 );
					break;
				}
				/* 7. IthakiCopter Telemetry Packet Transmission */
				case 7: {

					ithakiCopter( );
					break;
				}
				/* 8. OnBoard Car Fault Diagnostics (OBD-II) Packet Transmission */
				case 8: {

					vehicleOBD( );
					break;
				}
				/* 0. EXIT PROGRAM */
				case 0: {
					System.out.println(
					  		  " __________________________________________________________________\n"
							+ " |                                                                |\n"
							+ " |  EXITING PROGRAM, see you later :)                             |\n"
					  		+ " |________________________________________________________________|\n" );
					return ;
				}
				/* If none of the above was selected, then the key pressed was not valid */
				default: System.out.println(
				  		  " __________________________________________________________________\n"
						+ " |                                                                |\n"
						+ " |  Please choose one of the available options [0-7]              |\n"
						+ " |  ( Type in only the equivalent number )                        |\n"
				  		+ " |________________________________________________________________|\n" );		
			
			} // END of switch 

		} // END of infinite for loop

	}

	/* 1. Receive Echo Packets Function */
	public static void echoPacket( int pressedKey ) throws IOException {

		System.out.println(
		  		  " __________________________________________________________________\n"
				+ " |                                                                |\n"
				+ " | Receiving Echo Packets.....                                    |\n"	
				+ " |                                                                |\n"	);

		int count 		= 0;
		long startTime	= 0;
		long sendTime 	= 0;

		String packetInfo;
		String message 		= "";
		String responseTime = "";
		
		byte[] txbuffer, rxbuffer;

		PrintWriter resTimeFile;
		PrintWriter messagesFile;
		String echoCode;

		if( pressedKey == 1 ) {
		/* 1. For Echo Packets with random delay */
			resTimeFile = new PrintWriter( "../out/1. Echo Packets/1. With Delay/responseTime.txt" );
			messagesFile = new PrintWriter( "../out/1. Echo Packets/1. With Delay/messages.txt" );
			echoCode = userApplication.echoCode;

		} else {
		/* 0. For Echo Packets with no added delay */
			resTimeFile = new PrintWriter( "../out/1. Echo Packets/0. No Delay/responseTime.txt" );
			messagesFile = new PrintWriter( "../out/1. Echo Packets/0. No Delay/messages.txt" );
			echoCode = "E0000";			
		}

		DatagramSocket s = new DatagramSocket( );
		
		packetInfo = echoCode;
		txbuffer = packetInfo.getBytes( );
		
		DatagramPacket p = new DatagramPacket( txbuffer, txbuffer.length, hostAddress, serverPort );
		
		DatagramSocket r = new DatagramSocket( clientPort );
		
		r.setSoTimeout( 3000 );
		
		rxbuffer = new byte[32];
		
		DatagramPacket q = new DatagramPacket( rxbuffer, rxbuffer.length );
		
		startTime = System.currentTimeMillis();
        /* Receive Duration = 4 * 60 * 1000 = 240.000 ms = 4 minutes */		
		while(System.currentTimeMillis() - startTime <= 240000) {

			sendTime = System.currentTimeMillis();
			s.send( p );
			count++;
			
			try {
				r.receive( q );
				// Compute the Response Time and Write it in a string format into the equivalent file
				responseTime = String.valueOf( System.currentTimeMillis() - sendTime );
				resTimeFile.println( responseTime );
				// Get the whole message string and Write it into the equivalent file
				message = new String( rxbuffer, 0, q.getLength() );
				messagesFile.println( message );

			} catch ( Exception x ) {

				System.out.println( x );
			}
				
		}
		
		System.out.printf(
				  " | Finished.... Total Number of Echo Packets Received: %d         |\n"
		  		+ " |________________________________________________________________|\n", count );		
		
		s.close();
		r.close();
		resTimeFile.close();
		messagesFile.close();
		
	}

	/* 2. Receive Temperature Packets Function */
	public static void echoPacketTemperature( ) throws IOException {

		System.out.println(
		  		  " __________________________________________________________________\n"
				+ " |                                                                |\n"
				+ " | Receiving Temperature Packets.....                             |\n"	
				+ " |                                                                |\n"	);

		int i 	  = 0;
		int count = 0;

		String packetInfo;
		String message 			   = "";
		String echoTemperatureCode = echoCode;

		byte[] txbuffer, rxbuffer;

		
		PrintWriter tempMsgs = new PrintWriter( "../out/2. Temperature Packets/tempMsgs.txt" );
		
		DatagramSocket s = new DatagramSocket();
		
		packetInfo = echoCode;
		txbuffer = packetInfo.getBytes();

		DatagramPacket p = new DatagramPacket( txbuffer, txbuffer.length, hostAddress, serverPort );
		
		DatagramSocket r = new DatagramSocket( clientPort );
		
		r.setSoTimeout( 3000 );
		
		rxbuffer = new byte[54];
		
		DatagramPacket q = new DatagramPacket( rxbuffer, rxbuffer.length );
		
		for (i = 0; i < 100; i++) {
			
			/* CORRECT IS CURRENTLY MUTED DUE TO TECHNICAL ISSUES
			if ( i < 10 ) 
				echoTemperatureCode += "T0" + String.valueOf( i );
			else
				echoTemperatureCode += "T" + String.valueOf( i );
			*/

			// Currently only T00 is working, but as soon as the problem is resolved 
			// the above if has to restored ( uncommented )  
			echoTemperatureCode += "T00";

			txbuffer = echoTemperatureCode.getBytes();
			
			p.setData( txbuffer );
			p.setLength( txbuffer.length );
			
			s.send( p );
			count++;
			
			try {

				r.receive( q );
				message = new String( rxbuffer, 0, q.getLength() );

			} catch ( Exception x ) {

				System.out.println( x );
			}
			
			tempMsgs.println( message );
		}

		System.out.printf(
				  " | Finished.... Number of Temperature Packets Received: %d       |\n"
		  		+ " |________________________________________________________________|\n", count );		
						
		s.close();
		r.close();
		tempMsgs.close();
				
	}

	/* 3. Receive Camera Image Function */
	public static void imageReceive( int pressedKey ) throws IOException {

		String imageCode;

		int L = 128;

		byte[] txbuffer, txbufferNext, rxbuffer;
		
		rxbuffer = new byte[L];

		FileOutputStream imageFile;

		int flowON = 0;

		if( pressedKey > 0 ) {
		/* >=1. For Configuration CAM = PTZ + DIR */

			imageCode = userApplication.imageCode + "CAM=PTZ";

			switch ( pressedKey ) {

				/* 1. DIR = ' ' */
				case 1: {

					break;
				}
				/* 2. DIR = 'L' */
				case 2: {

					imageCode += "DIR=L";
					break;
				}
				/* 3. DIR = 'U' */
				case 3: {

					imageCode += "DIR=U";
					break;
				}
				/* 4. DIR = 'R' */
				case 4: {

					imageCode += "DIR=R";
					break;
				}
				/* 5. DIR = 'D' */
				case 5: {

					imageCode += "DIR=D";
					break;
				}
				/* 6. DIR = 'M' */
				case 6: {

					imageCode += "DIR=M";
					break;
				}
				/* 7. DIR = 'C' */
				case 7: {

					imageCode += "DIR=C";
					break;
				}
			}

		} else
		/* 0. For Configuration CAM = FIX */
			imageCode = userApplication.imageCode + "CAM=FIX";	

		/* Add Flow Control Option or Not */
		for ( ;; ) {
			
			System.out.println(
			  		  " __________________________________________________________________\n"
					+ " |                                                                |\n"
					+ " |  F L O W   C O N T R O L   O P T I O N S :                     |\n"
			  		+ " |________________________________________________________________|\n"
					+ " |                                                                |\n"
					+ " |  0. FLOW = OFF                                                 |\n"
					+ " |  1. FLOW = ON                                                  |\n"
					+ " |________________________________________________________________|\n"
					+ "\n Select one of the available options by pressing the mapped key: "		);

			input = new Scanner( System.in );
			flowON = input.nextInt();

			if ( flowON == 1 ) {
			// Add FLOW = ON and continue
				imageCode += "FLOW=ON";
				break;
						
			} else if ( flowON == 0 ){
			// Add nothing and continue
				break;

			} else {
			
				System.out.println(
				  		  " __________________________________________________________________\n"
						+ " |                                                                |\n"
						+ " |  Please choose one of the available options [0-1]              |\n"
						+ " |  ( Type in only the equivalent number )                        |\n"
				  		+ " |________________________________________________________________|\n" );		
			}
		}

		/* Determine the packet length */
		for ( ;; ) {
			
			System.out.println(
			  		  " __________________________________________________________________\n"
					+ " |                                                                |\n"
					+ " |  P A C K E T   L E N G T H   O P T I O N S :                   |\n"
			  		+ " |________________________________________________________________|\n"
					+ " |                                                                |\n"
					+ " |  0. UDP = L =  128 ( default )                                 |\n"
					+ " |  1. UDP = L =  256                                             |\n"
					+ " |  2. UDP = L =  512                                             |\n"
					+ " |  3. UDP = L = 1024                                             |\n"
					+ " |________________________________________________________________|\n"
					+ "\n Select one of the available options by pressing the mapped key: "		);

			input = new Scanner( System.in );
			int lengthSelection = input.nextInt();

			switch ( lengthSelection ) {

				/* 0. UDP = L =  128 */
				case 0: {

					imageCode += "UDP=" + String.valueOf( L );
					break;
				}
				/* 1. UDP = L =  256 */
				case 1: {

					L = 256;
					imageCode += "UDP=" + String.valueOf( L );
					rxbuffer = new byte[256];
					break;
				}
				/* 2. UDP = L =  512 */
				case 2: {

					L = 512;
					imageCode += "UDP=" + String.valueOf( L );
					rxbuffer = new byte[512];
					break;
				}
				/* 3. UDP = L = 1024 */
				case 3: {

					L = 1024;
					imageCode += "UDP=" + String.valueOf( L );
					rxbuffer = new byte[1024];
					break;
				}
				/* */
				default: {
					
					System.out.println(
					  		  " __________________________________________________________________\n"
							+ " |                                                                |\n"
							+ " |  Please choose one of the available options [0-3]              |\n"
							+ " |  ( Type in only the equivalent number )                        |\n"
					  		+ " |________________________________________________________________|\n" );
				}			
			}

			if ( (lengthSelection >= 0) && (lengthSelection <= 3) )
				break;
		}		
		long sendTime = 0;
		String responseTime = "";
		String resTimeFileName = "";

		if( pressedKey > 0 ) {

			resTimeFileName = "../out/3. Camera Image/1. PTZ_imageResponseTime_";

			if ( flowON == 1 ) {
				imageFile = new FileOutputStream ( "../out/3. Camera Image/1. PTZ_image_FLOW_ON.jpg" );
				resTimeFileName += "FLOW_ON_";	
			}
			else
				imageFile = new FileOutputStream ( "../out/3. Camera Image/1. PTZ_image.jpg" );
			
			resTimeFileName += String.valueOf( L ) + ".txt";
		} else {

			resTimeFileName = "../out/3. Camera Image/0. FIX_imageResponseTime_";
			if ( flowON == 1 ) {
				imageFile = new FileOutputStream ( "../out/3. Camera Image/0. FIX_image_FLOW_ON.jpg" );
				resTimeFileName += "FLOW_ON_";
			}
			else
				imageFile = new FileOutputStream ( "../out/3. Camera Image/0. FIX_image.jpg" );

			resTimeFileName += String.valueOf( L ) + ".txt";
		}

		PrintWriter resTimeFile = new PrintWriter( resTimeFileName );

		DatagramSocket s = new DatagramSocket();
		
		txbuffer = imageCode.getBytes();
		
		DatagramPacket p = new DatagramPacket( txbuffer, txbuffer.length, hostAddress, serverPort );

		String nextString = "NEXT";

		txbufferNext = nextString.getBytes();
		
		DatagramPacket next = new DatagramPacket( txbufferNext, txbufferNext.length, hostAddress, serverPort );

		
		DatagramSocket r = new DatagramSocket( clientPort );

		r.setSoTimeout( 9000 );
		
		DatagramPacket q = new DatagramPacket( rxbuffer, rxbuffer.length );
	
		s.send( p );

		if( pressedKey > 1 )
		// If DIR parameter was set:
		// Sleep for 4 secs in order for the servo to tilt the camera appropriately
			try {

			    Thread.sleep( 4000 );

			} catch(InterruptedException e) {

				System.out.println( e );
			}			

		
		if ( flowON == 1 )
			sendTime = System.currentTimeMillis();
		
		System.out.println(
		  		  " __________________________________________________________________\n"
				+ " |                                                                |\n"
				+ " | Receiving Camera Image.....                                    |\n"	
				+ " |                                                                |\n"	);

		int retransmissionFlag = 0;

		for( ;; ) {

			try {

				r.receive( q );
				
				retransmissionFlag = 0;

				if ( flowON == 1 ) {
					// Compute the Response Time and Write it in a string format into the equivalent file
					responseTime = String.valueOf( System.currentTimeMillis() - sendTime );
					resTimeFile.println( responseTime );
					// Update the sendTime for the next packet
					sendTime = System.currentTimeMillis();
					s.send( next );
				}
				// Write the new rxbuffer content into the image file 
				imageFile.write( rxbuffer );
				imageFile.flush();
			
			} catch( Exception x ) {

				System.out.println( x );

				if ( retransmissionFlag == 1 )
				// Implementation of the one retransmission try of the server
					break;
				
				retransmissionFlag = 1;
			}
		}
		
		System.out.println(
				  " | Finished.... Image Received                                    |\n"
		  		+ " |________________________________________________________________|\n" );		
		
		r.close();
		s.close();
		imageFile.close();
		resTimeFile.close();
		
	}


	/* 4. Receive Virtual Frequency Generator Packets Function */
	/* 5. Receive DPCM Sound Clip Function */
	public static void soundDPCM( int mode ) throws IOException, LineUnavailableException {

		String soundCodeDPCM = soundCode;

		PrintWriter samples;
		PrintWriter samplesDiffs;

		int Q = 8;

		if ( mode == 0 ) {
		// mode == 0, so Virtual Frequency Generator Packets are requested

			soundCodeDPCM += "T999";

			samples 	 = new PrintWriter( "../out/4. Virtual Frequency Generator/Frequencies.txt" );
			samplesDiffs = new PrintWriter( "../out/4. Virtual Frequency Generator/FrequenciesDiffs.txt" );

		} else {
		// mode == 1, so DPCM Sound Clip is requested
			
			soundCodeDPCM += SelectSong( );

			int selectQ;

			for ( ;; ) {
				
				System.out.println(
				  		  " __________________________________________________________________\n"
						+ " |                                                                |\n"
						+ " |  A U D I O  Q U A L I T Y   S E L E C T I O N :                |\n"
				  		+ " |________________________________________________________________|\n"
						+ " |                                                                |\n"
						+ " |  0. Q =  8                                                     |\n"
						+ " |  1. Q = 16                                                     |\n"
						+ " |________________________________________________________________|\n\n"	);

				input = new Scanner( System.in );
				selectQ = input.nextInt();

				if ( (selectQ != 1) && (selectQ != 0) ) {
					
					System.out.println(
					  		  " __________________________________________________________________\n"
							+ " |                                                                |\n"
							+ " |  Please choose one of the available options [0-1]              |\n"
							+ " |  ( Type in only the equivalent number )                        |\n"
					  		+ " |________________________________________________________________|\n" );		
				} else {

					if ( selectQ == 1 ) Q = 16;
					break;
				}
			}

			soundCodeDPCM += "F999";

			samples 	 = new PrintWriter( "../out/5. DPCM Sound/SamplesDPCM.txt" );
			samplesDiffs = new PrintWriter( "../out/5. DPCM Sound/SamplesDiffsDPCM.txt" );
		}

		int i 			 = 0;
		int bpp 		 = 128;
		int soundPackets = 999;
		int samplesSize  = 2 * bpp * soundPackets;
		int step 		 = 1;

		int[] soundDiffs 	 = new int[samplesSize];
		int[] soundSamples 	 = new int[samplesSize];
		byte[] receivedSound = new byte[bpp * soundPackets];
		byte[] audioBufferOut = new byte[samplesSize];
		
		byte[] txbuffer, rxbuffer;
		
		
		DatagramSocket s = new DatagramSocket();
		
		txbuffer = soundCodeDPCM.getBytes();
		
		DatagramPacket p = new DatagramPacket( txbuffer, txbuffer.length, hostAddress, serverPort );
		
		DatagramSocket r = new DatagramSocket( clientPort );
		
		r.setSoTimeout( 7000 );
		rxbuffer = new byte[bpp];
		
		DatagramPacket q = new DatagramPacket( rxbuffer, rxbuffer.length );
		
		s.send( p );
		

		if ( mode == 0 ) {

			System.out.println(
			  		  " __________________________________________________________________\n"
					+ " |                                                                |\n"
					+ " | Receiving Virtual Frequency Generator Packets.....             |\n"	
					+ " |                                                                |\n"	);
		} else {

			System.out.println(
			  		  " __________________________________________________________________\n"
					+ " |                                                                |\n"
					+ " | Receiving DPCM Sound Clip.....                                 |\n"	
					+ " |                                                                |\n"	);
		}

		for( ;; ) {

			try {

				r.receive( q );
				for(int j = 0; j < bpp; j++)
					receivedSound[i*bpp + j] = rxbuffer[j];
				i++;

			} catch ( Exception x ) {

				System.out.println( x );
				break;
			}
		}
		
		if ( mode == 0 ) {

			System.out.printf(
					  " |                                                                |\n"
					+ " | Finished.... Virtual Frequency Generator Packets Received: %d |\n"
					+ " |----------------------------------------------------------------|\n"
					+ " |                                                                |\n"
					+ " | Processing....                                                 |\n"
					+ " |                                                                |\n", i );
		} else {

			System.out.printf(
					  " |                                                                |\n"
					+ " | Finished.... Number of DPCM Sound Packets Received: %d        |\n"
					+ " |----------------------------------------------------------------|\n"
					+ " |                                                                |\n"
					+ " | Sound Clip Processing....                                      |\n"
					+ " |                                                                |\n", i );
		}


		AudioFormat linearPCM = new AudioFormat( 8000, Q, 1, true, false );
		SourceDataLine lineOut = AudioSystem.getSourceDataLine( linearPCM );
		lineOut.open( linearPCM, samplesSize ); 
		
		for (i = 0; i < bpp * soundPackets; i++) {
			int k = (int) receivedSound[i];
			soundDiffs[2*i + 1] = ((k & 15) - 8);
			soundDiffs[2*i] 	= (((k >> 4) & 15) - 8);
			// Clipping through & 15
		}
				
		soundSamples[0]   = 0;
		audioBufferOut[0] = (byte) (soundSamples[0]);
		for(i = 1; i < samplesSize; i++) {
			soundSamples[i]   =  step * soundDiffs[i] + soundSamples[i-1];
			audioBufferOut[i] = (byte) ( soundSamples[i] & 0x00FF ); // added (& 0xFF)	
		}
		
		for(i = 0; i < samplesSize; i++){
			samples.println( audioBufferOut[i] );
			samplesDiffs.println( soundDiffs[i] );
		}

		System.out.printf(
				  " |                                                                |\n"
				+ " | Finished Processing....                                        |\n"
				+ " |----------------------------------------------------------------|\n"
				+ " |                                                                |\n"
				+ " | Playing Sound Clip ....                                        |\n"
				+ " |                                                                |\n" );

		lineOut.start();
		lineOut.write( audioBufferOut, 0, samplesSize );
		lineOut.drain();

		System.out.println(
				  " | Finished....                                                   |\n"
		  		+ " |________________________________________________________________|\n" );		

		lineOut.close();
		s.close();
		r.close();
		samples.close();
		samplesDiffs.close();
				
	}

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

	/* 7. Receive Ithaki Copter Telemetry Packets Function */
	public static void ithakiCopter( ) throws IOException {

		int portNumber = 38048;

		long startTime	= 0;

		System.out.println(
		  		  " __________________________________________________________________\n"
				+ " |                                                                |\n"
				+ " | Receiving Ithaki Copter Telemetry Packets.....                 |\n"	
				+ " |                                                                |\n"	);

		int flightLevel = 175;
		int lMotor 		= 150;
		int rMotor 		= 150;

		for(int i = 0; i < 2; i++) {

			String autopilot = "AUTO FLIGHTLEVEL=" + String.valueOf( flightLevel ) + " LMOTOR=" + String.valueOf( lMotor ) + " RMOTOR=" + String.valueOf( rMotor ) + " PILOT \r\n";
			String response = "";
			String folderPath	= "../out/7. Ithaki Copter/";
			String fileName 	= "copter" + String.valueOf( flightLevel ) + ".txt";
			PrintWriter copter 	= new PrintWriter( folderPath + fileName );
			
			Socket s = new Socket( hostAddress, portNumber );
			
			s.setSoTimeout( 2000 );
			
			InputStream  in  = s.getInputStream();
			OutputStream out = s.getOutputStream();
			
			startTime = System.currentTimeMillis();
	        /* Receive Duration = 2 * 60 * 1000 = 120.000 ms = 2 minutes */		
			while(System.currentTimeMillis() - startTime <= 120000) {

				SendTCP( out, autopilot );

				response = ReceiveTCP( in );

				System.out.print( response );
				copter.print( response );
			}
			
			s.close();
			copter.close();
		
			flightLevel += 50;
			lMotor 		+= 50;
			rMotor 		+= 50;
		}

		System.out.println(
				  " | Finished.... Ithaki Copter Telemetry Packets Received          |\n"
		  		+ " |________________________________________________________________|\n" );		
			
	}

	/* 8. Receive OnBoard Car Fault Diagnostics (OBD-II) Packets Function */
    public static void vehicleOBD( ) throws IOException {
		
		int portNumber = 29078;

		String folderPath = "../out/8. OBD-II/";
		String obdCode = "01 11\r";

		PrintWriter engineRunTimeFile 		= new PrintWriter( folderPath + "EngineRunTime.txt" );
		PrintWriter intakeAirPressureFile 	= new PrintWriter( folderPath + "1. IntakeAirPressure.txt" );
		PrintWriter throttlePositionFile	= new PrintWriter( folderPath + "2. ThrottlePosition.txt" );
		PrintWriter rpmFile					= new PrintWriter( folderPath + "3. RPM.txt" );
		PrintWriter speedFile				= new PrintWriter( folderPath + "4. Speed.txt" );
		PrintWriter carTemperatureFile		= new PrintWriter( folderPath + "5. CarTemperature.txt");

		String engineRunTimeCode		= "01 1F\r";    	int startTime, currentTime;
		String intakeAirPressureCode	= "01 0F\r";    	int airTemp;
		String throttlePositionCode		= "01 11\r";    	int throttlePos;
		String rpmCode					= "01 0C\r";    	int rpm;
		String speedCode				= "01 0D\r";    	int speed;
		String carTemperatureCode		= "01 05\r";    	int carTemp;

		System.out.println(
		  		  " __________________________________________________________________\n"
				+ " |                                                                |\n"
				+ " | Receiving OBD-II Packets.....                                  |\n"	
				+ " |                                                                |\n"	);

		Socket s = new Socket( hostAddress, portNumber );
		
		s.setSoTimeout( 10000 );

		InputStream in 	 = s.getInputStream();
		OutputStream out = s.getOutputStream();
		

    	/* Engine Run Time */
    	SendTCP( out, engineRunTimeCode );
		String[] parsed = ReceiveTCP( in ).split( " " );

		startTime = Integer.parseInt( parsed[2], 16 ) * 256 + Integer.parseInt( parsed[3], 16 );
		
		System.out.println( "--------------------" );
		System.out.println( "Engine Runtime: " + startTime );

		engineRunTimeFile.print( String.valueOf( startTime ) + "\n" ); 
	
		for( ;; ) {

	        /* 1. Intake Air Temperature */
	    	SendTCP( out, intakeAirPressureCode );
			parsed = ReceiveTCP( in ).split( " " );

			airTemp = Integer.parseInt( parsed[2], 16 ) - 40;
			
			System.out.println( "Air Temperature: " + airTemp );

			intakeAirPressureFile.print( String.valueOf( airTemp ) + "\n" );

	        /* 2. Throttle Position */
	    	SendTCP( out, throttlePositionCode );
			parsed = ReceiveTCP( in ).split( " " );

			throttlePos = Integer.parseInt( parsed[2], 16 ) * 100 / 255;
			
			System.out.println( "Throttle Position: " + throttlePos );

			throttlePositionFile.print( String.valueOf( throttlePos ) + "\n" );

			/* 3. Engine RPM */
	    	SendTCP( out, rpmCode );
			parsed = ReceiveTCP( in ).split( " " );

			rpm = ( Integer.parseInt( parsed[2], 16 ) * 256 + Integer.parseInt( parsed[3], 16 ) ) / 4;
			
			System.out.println( "Engine RPM: " + rpm );

			rpmFile.print( String.valueOf( rpm ) + "\n" );

	        /* 4. Vehicle Speed */
	    	SendTCP( out, speedCode );
			parsed = ReceiveTCP( in ).split( " " );

			speed = Integer.parseInt( parsed[2], 16 ) ;
			
			System.out.println( "Vehicle Speed: " + speed );

			speedFile.print( String.valueOf( speed ) + "\n" );

	        /* 5. Coolant Temperature */		
	    	SendTCP( out, carTemperatureCode );
			parsed = ReceiveTCP( in ).split( " " );

			carTemp = Integer.parseInt( parsed[2], 16 ) - 40;
			
			System.out.println( "Car Temperature: " + carTemp );
			System.out.println( "--------------------" );

			carTemperatureFile.print( String.valueOf( carTemp ) + "\n" );

	    	/* Engine Run Time */
	    	SendTCP( out, engineRunTimeCode );
			parsed = ReceiveTCP( in ).split( " " );

			currentTime = Integer.parseInt( parsed[2], 16 ) * 256 + Integer.parseInt( parsed[3], 16 );
			
			System.out.println( "Engine Runtime: " + currentTime );

			if ( currentTime - startTime > 240 )
	        // If the receive duration = 4 * 60 = 240 secs = 4 minutes has expired we are done
				break;

			engineRunTimeFile.print( String.valueOf( currentTime - startTime ) + "\n" ); 

		} // END of infinite for loop 

		System.out.println(
				  " | Finished.... OBD-II Packets Received                           |\n"
		  		+ " |________________________________________________________________|\n" );


		engineRunTimeFile.close();
		intakeAirPressureFile.close();
		throttlePositionFile.close();
		rpmFile.close();
		speedFile.close();
		carTemperatureFile.close();

		s.close();
	}

	private static void SendTCP(OutputStream out, String msg) {

		byte[] sendBuff = msg.getBytes();

		try {
			out.write(sendBuff);
		}
		catch ( IOException x ) {

			System.out.println( x );
		}
	}

	private static String ReceiveTCP ( InputStream in ) {

		int k;
		String msg = "";
		for ( ;; ) {

			try {

				k = in.read();
				if ( ( k ==-1 ) || ( k == 13 ) )
					return msg;
				msg += (char) k;
			}
			catch ( IOException x ) {

				System.out.println( x );
				return "Error";
			}

		}
	}

	private static String SelectSong ( ) {

		int pressedKey;

		for ( ;; ) {
			
			System.out.println(
			  		  " __________________________________________________________________\n"
					+ " |                                                                |\n"
					+ " |  T R A C K   N U M B E R   S E L E C T I O N :                 |\n"
			  		+ " |________________________________________________________________|\n"
					+ " |                                                                |\n"
					+ " | Type in a two digit decimal number [1-99].                     |\n"
					+ " |                                                                |\n"
					+ " | Type [0], if you want to play a song randomly.                 |\n"
					+ " |________________________________________________________________|\n\n"	);

			input = new Scanner( System.in );
			pressedKey = input.nextInt();

			if ( (pressedKey < 0) || (pressedKey > 99) ) {
				
				System.out.println(
				  		  " __________________________________________________________________\n"
						+ " |                                                                |\n"
						+ " |  Number out of bounds.                                         |\n"
						+ " |  Please type in a two digit decimal number [0-99]              |\n"
				  		+ " |________________________________________________________________|\n\n" );		
			} else {

				break;
			}
		}
		
		if ( pressedKey == 0 ) 
			return "";
		else if ( pressedKey < 10 ) 
			return ( "L0" + String.valueOf( pressedKey ) );
		else
			return ( "L" + String.valueOf( pressedKey ) );

	}

}
