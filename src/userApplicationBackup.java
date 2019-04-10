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
	
	private static int portNumber			= 26;
	private static int clientPort 			= 48000 + portNumber;
	private static int serverPort 			= 38000 + portNumber;
	private static String echoCode 			= "E6468";
	private static String imageCode 		= "M8225";
	private static String soundCode 		= "A1446"; 
	private static String ithakiCopterCode 	= "Q9826";
	private static String obdVehicleCode 	= "V3762";
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

					soundVFG( );
					break;
				}				
				/* 5. DPCM Sound Packet Transmission */
				case 5: {

					soundDPCM( );
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
				+ " | Receiving Echo Packets .....                                   |\n"	
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
				+ " | Receiving Temperature Packets .....                            |\n"	
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
				+ " | Receiving Camera Image .....                                   |\n"	
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
	public static void soundVFG( ) throws IOException, LineUnavailableException {

		String soundCodeDPCM = soundCode + "T999";
/*
		int i 			 = 0;
		int bpp 		 = 128;
		int soundPackets = 999;
		int samplesSize  = 2 * bpp * soundPackets;

		int[] soundSamples 	 = new int[samplesSize];
		int[] soundClip 	 = new int[samplesSize];
		byte[] receivedSound = new byte[bpp * soundPackets];
		
		byte[] txbuffer, rxbuffer;
		
		PrintWriter freqs = new PrintWriter( "../out/4. Virtual Frequency Generator/Frequencies.txt" );		
		
		DatagramSocket s = new DatagramSocket();
		
		txbuffer = soundCodeDPCM.getBytes();
		
		DatagramPacket p = new DatagramPacket( txbuffer, txbuffer.length, hostAddress, serverPort );
		
		DatagramSocket r = new DatagramSocket( clientPort );
		
		r.setSoTimeout( 7000 );
		rxbuffer = new byte[bpp];
		
		DatagramPacket q = new DatagramPacket( rxbuffer, rxbuffer.length );
		
		s.send( p );
		
		System.out.println(
		  		  " __________________________________________________________________\n"
				+ " |                                                                |\n"
				+ " | Receiving Virtual Frequency Generator Packets .....            |\n"	
				+ " |                                                                |\n"	);
		
		// TODO: Complete VFG Function Here

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
		
		System.out.printf(
				  " |                                                                |\n"
				+ " | Finished.... Number of DPCM Sound Packets Received: %d        |\n"
				+ " |----------------------------------------------------------------|\n"
				+ " |                                                                |\n"
				+ " | Sound Clip Processing....                                      |\n"
				+ " |                                                                |\n", i );


		AudioFormat linearPCM = new AudioFormat( 8000, 8, 1, true, false );
		SourceDataLine lineOut = AudioSystem.getSourceDataLine( linearPCM );
		lineOut.open( linearPCM, samplesSize ); 
		
		for (i = 0; i < bpp * soundPackets; i++) {
			int k = (int) receivedSound[i];
			soundSamples[2*i] 	  = (((k >> 4) & 15) - 8);
			soundSamples[2*i + 1] = ((k & 15) - 8);
		}
		
		
		byte[] audioBufferOut = new byte[samplesSize];
		
		for (i = 0; i < samplesSize; i++) // i = 1 --> i = 0
			soundClip[i] = soundSamples[i];
		
		audioBufferOut[0] = (byte) (2*soundClip[0]);
		for(i = 1; i < samplesSize; i++) {
			soundClip[i] 	  =  2*soundClip[i] + audioBufferOut[i-1];
			audioBufferOut[i] = (byte) soundClip[i];	
		}
		
		for(i = 0; i < samplesSize; i++){
			samples.println( audioBufferOut[i] );
			samplesDiffs.println( soundSamples[i] );
		}
		
		lineOut.start();
		lineOut.write( audioBufferOut, 0, samplesSize );
		lineOut.stop();

		System.out.println(
				  " | Finished.... Sound Clip Received                               |\n"
		  		+ " |________________________________________________________________|\n" );		
		
		lineOut.close();
		s.close();
		r.close();
		samples.close();
		samplesDiffs.close();
			*/	
	}

	/* 5. Receive DPCM Sound Clip Function */
	public static void soundDPCM( ) throws IOException, LineUnavailableException {

		String soundCodeDPCM = soundCode;
		
		int pressedKey;

		for ( ;; ) {
			
			System.out.println(
			  		  " __________________________________________________________________\n"
					+ " |                                                                |\n"
					+ " |  T R A C K   N U M B E R   S E L E C T I O N :                 |\n"
			  		+ " |________________________________________________________________|\n"
					+ " |                                                                |\n"
					+ " | Type in a two digit decimal number [0-99]                      |\n"
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

				break;
			}
		}
		
		if ( pressedKey < 10 ) 
			soundCodeDPCM += "L0" + String.valueOf( pressedKey );
		else
			soundCodeDPCM += "L" + String.valueOf( pressedKey );

		soundCodeDPCM += "F999";

		int i 			 = 0;
		int bpp 		 = 128;
		int soundPackets = 999;
		int samplesSize  = 2 * bpp * soundPackets;

		int[] soundSamples 	 = new int[samplesSize];
		int[] soundClip 	 = new int[samplesSize];
		byte[] receivedSound = new byte[bpp * soundPackets];
		
		byte[] txbuffer, rxbuffer;
		
		PrintWriter samples 	 = new PrintWriter( "../out/5. DPCM Sound/SamplesDPCM.txt" );
		PrintWriter samplesDiffs = new PrintWriter( "../out/5. DPCM Sound/SamplesDiffsDPCM.txt" );
		
		
		DatagramSocket s = new DatagramSocket();
		
		txbuffer = soundCodeDPCM.getBytes();
		
		DatagramPacket p = new DatagramPacket( txbuffer, txbuffer.length, hostAddress, serverPort );
		
		DatagramSocket r = new DatagramSocket( clientPort );
		
		r.setSoTimeout( 7000 );
		rxbuffer = new byte[bpp];
		
		DatagramPacket q = new DatagramPacket( rxbuffer, rxbuffer.length );
		
		s.send( p );
		
		System.out.println(
		  		  " __________________________________________________________________\n"
				+ " |                                                                |\n"
				+ " | Receiving DPCM Sound Clip .....                                |\n"	
				+ " |                                                                |\n"	);
		
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
		
		System.out.printf(
				  " |                                                                |\n"
				+ " | Finished.... Number of DPCM Sound Packets Received: %d        |\n"
				+ " |----------------------------------------------------------------|\n"
				+ " |                                                                |\n"
				+ " | Sound Clip Processing....                                      |\n"
				+ " |                                                                |\n", i );


		AudioFormat linearPCM = new AudioFormat( 8000, 8, 1, true, false );
		SourceDataLine lineOut = AudioSystem.getSourceDataLine( linearPCM );
		lineOut.open( linearPCM, samplesSize ); 
		
		for (i = 0; i < bpp * soundPackets; i++) {
			int k = (int) receivedSound[i];
			soundSamples[2*i] 	  = (((k >> 4) & 15) - 8);
			soundSamples[2*i + 1] = ((k & 15) - 8);
		}
		
		
		byte[] audioBufferOut = new byte[samplesSize];
		
		for (i = 0; i < samplesSize; i++) // i = 1 --> i = 0
			soundClip[i] = soundSamples[i];
		
		audioBufferOut[0] = (byte) (2*soundClip[0]);
		for(i = 1; i < samplesSize; i++) {
			soundClip[i] 	  =  2*soundClip[i] + audioBufferOut[i-1];
			audioBufferOut[i] = (byte) soundClip[i];	
		}
		
		for(i = 0; i < samplesSize; i++){
			samples.println( audioBufferOut[i] );
			samplesDiffs.println( soundSamples[i] );
		}
		
		lineOut.start();
		lineOut.write( audioBufferOut, 0, samplesSize );
		lineOut.stop();

		System.out.println(
				  " | Finished.... Sound Clip Received                               |\n"
		  		+ " |________________________________________________________________|\n" );		
		
		lineOut.close();
		s.close();
		r.close();
		samples.close();
		samplesDiffs.close();
				
	}

	/* 6. Receive AQ-DPCM Sound Clip Function */
	public static void soundAQDPCM( int audioClipNumber ) throws IOException, LineUnavailableException {

		String soundCodeAQDPCM = soundCode + "AQF999";

		System.out.println(
		  		  " __________________________________________________________________\n"
				+ " |                                                                |\n"
				+ " | Receiving AQ-DPCM Sound Clip %d .....                           |\n"	
				+ " |                                                                |\n", audioClipNumber );

		int i 			 = 0;
		int count 		 = 0;
		int bpp 		 = 132;
		int soundPackets = 999;
		int samplesSize  = 2*bpp*soundPackets;
		int lsb 		 = 0;
		int msb 		 = 0;

		int[] mean 			 = new int[soundPackets];
		int[] step 			 = new int[soundPackets];
		int[] soundSamples 	 = new int[samplesSize];
		int[] soundClip 	 = new int[samplesSize];
		byte[] receivedSound = new byte[bpp*soundPackets];

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
		lineOut.open( linearPCM, samplesSize ); 
		
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
				soundSamples[2*count] 	= (((k >> 4) & 15) - 8) * step[i];
				soundSamples[2*count + 1] = ((k & 15) - 8) * step[i];
				count++;
			}
		}
		
		byte[] audioBufferOut = new byte[512 * soundPackets]; //512 = 128*2*soundPackets*2
			
		for(i = 1; i < 256 * soundPackets; i++)
			soundClip[i] = soundSamples[i];
			
		for(i = 0; i < soundPackets; i++) {
			for(int j = 0; j < 256; j++) {
				if( (i == 0) && (j == 0) ) continue;
					soundClip[i*256 + j] = soundClip[i*256 + j] + soundClip[i*256 + j - 1];	
			}
		}
			
		for(i = 0; i < 256 * soundPackets; i++){
			audioBufferOut[2*i] 	= (byte) (soundClip[i] & 0xFF);
			audioBufferOut[2*i + 1] = (byte) ((soundClip[i] >> 8) & 0xFF);
		}
		
		for(i = 0; i < 256 * soundPackets; i++) {
			samples.println( audioBufferOut[i] );
			samplesDiffs.println( soundSamples[i] );
		}
					
		lineOut.start();
		lineOut.write( audioBufferOut, 0, audioBufferOut.length );
		lineOut.stop();
		
		System.out.println(
				  " | Finished.... Sound Clip %d Received                             |\n"
		  		+ " |________________________________________________________________|\n", audioClipNumber );	

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

		long startTime	= 0;

		System.out.println(
		  		  " __________________________________________________________________\n"
				+ " |                                                                |\n"
				+ " | Receiving Ithaki Copter Telemetry Packets .....                |\n"	
				+ " |                                                                |\n"	);


		int portNumber = 38048;

		PrintWriter copter = new PrintWriter( "../out/7. Ithaki Copter/copter.txt" );
		
		Socket s = new Socket( hostAddress, portNumber );
		
		s.setSoTimeout( 1000 );
		
		InputStream in 	 = s.getInputStream();
		OutputStream out = s.getOutputStream();
		
		startTime = System.currentTimeMillis();
        /* Receive Duration = 2 * 60 * 1000 = 120.000 ms = 2 minutes */		
		while(System.currentTimeMillis() - startTime <= 120000) {

			try {
				out.write( "AUTO FLIGHTLEVEL=150 LMOTOR=125 RMOTOR=125 PILOT \r\n".getBytes() );
				int k = in.read();
				System.out.print( (char) k );
				copter.print( (char) k );

			} catch( Exception x ) {

				System.out.println( x );
				break;
			}
		}
		
		s.close();
		copter.close();
		
		System.out.println(
				  " | Finished.... Ithaki Copter Telemetry Packets Received          |\n"
		  		+ " |________________________________________________________________|\n" );		
			
	}

	/* 8. Receive OnBoard Car Fault Diagnostics (OBD-II) Packets Function */
    public static void vehicleOBD( ) throws IOException {
		
    	long startTime;
		int portNumber = 29078;
		
		PrintWriter engineRunTimeFile;
		PrintWriter intakeAirPressureFile;
		PrintWriter throttlePositionFile;
		PrintWriter rpmFile;
		PrintWriter speedFile;
		PrintWriter carTemperatureFile;

		String folderPath = "../out/8. OBD-II/";
		String obdCode = "01 11\r";
		
		for( ;; ) {

			Socket send = new Socket( hostAddress, portNumber );
			
			send.setSoTimeout( 1000 );

			InputStream in 	 = send.getInputStream();
			OutputStream out = send.getOutputStream();
			
			System.out.println(
					  " __________________________________________________________________\n"
					+ " |                                                                |\n"
					+ " |  V E H I C L E   O B D - II   O P T I O N S   T A B L E :      |\n"
					+ " |________________________________________________________________|\n"
					+ " |                                                                |\n"
					+ " | 1. Engine Run Time.                                            |\n"
					+ " | 2. Intake Air Temperature.                                     |\n"
					+ " | 3. Throttle Position.                                          |\n"
					+ " | 4. Engine RPM.                                                 |\n"
					+ " | 5. Vehicle Speed.                                              |\n"
					+ " | 7. Coolant Temperature.                                        |\n"
					+ " | -. ANY OTHER KEY RETURNS BACK TO THE MAIN APPLICATION MENU     |\n"
					+ " |________________________________________________________________|\n"
					+ "\n Select one of the available options by pressing the mapped key: " 	);
			                
	        
	        input = new Scanner( System.in );
			int pressedKey = input.nextInt();
	      
	        switch( pressedKey ) {

	        	/* 1. Engine Run Time */
			 	case 1: {

					engineRunTimeFile = new PrintWriter( folderPath + "1. EngineRunTime.txt" );
					obdCode = "01 1F\r";
					break;
		        } 
		        /* 2. Intake Air Temperature */
			 	case 2: {

					 engineRunTimeFile = new PrintWriter( folderPath + "2. IntakeAirPressure.txt" );
					 obdCode = "01 0F\r";
					 break;
		        } 
		        /* 3. Throttle Position */
			 	case 3: {

					 engineRunTimeFile = new PrintWriter( folderPath + "3. ThrottlePosition.txt" );
					 obdCode = "01 11\r";
					 break;
		        } 
		        /* 4. Engine RPM */
			 	case 4: {

					 engineRunTimeFile = new PrintWriter( folderPath + "4. RPM.txt" );
					 obdCode = "01 0C\r";
					 break;
		        } 
		        /* 5. Vehicle Speed */
			 	case 5: {

					 engineRunTimeFile = new PrintWriter( folderPath + "5. Speed.txt" );
					 obdCode = "01 0D\r";
					 break;
		        } 
		        /* 6. Coolant Temperature */
			 	case 6: {

					 engineRunTimeFile = new PrintWriter( folderPath + "6. CarTemperature.txt");
					 obdCode = "01 05\r";
					 break;
		        } 
				/* If none of the above was selected, RETURN BACK TO MAIN APPLICATION MENU */
				default: {

					System.out.println(
					  		  " __________________________________________________________________\n"
							+ " |                                                                |\n"
							+ " |  RETURNING BACK TO MAIN APPLICATION MENU                       |\n"
					  		+ " |________________________________________________________________|\n" );
			
					return ;
		        } 
	        
	        } // END of switch
	        
			System.out.println(
			  		  " __________________________________________________________________\n"
					+ " |                                                                |\n"
					+ " | Receiving OBD-II Packets .....                                 |\n"	
					+ " |                                                                |\n"	);

	        startTime = System.currentTimeMillis();
	        /* Receive Duration = 4 * 60 * 1000 = 240.000 ms = 4 minutes */
	        while( System.currentTimeMillis() - startTime <= 240000 ) {

				try {

					out.write( obdCode.getBytes() );
					int k = in.read();
					System.out.print( (char) k );

					engineRunTimeFile.print( (char) k );
					
				} catch( Exception x ) {

					System.out.println( x );
					break;
				}
			}
		
			System.out.println(
					  " | Finished.... OBD-II Packets Received                           |\n"
			  		+ " |________________________________________________________________|\n" );		
			
			engineRunTimeFile.close();
			send.close();
				
		} // END of infinite for loop 
    }

}