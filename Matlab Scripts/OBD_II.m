% NAME:     Flionis Ioannis
% AEM:      8263
% CONTACT:  iflionis@auth.gr

clear all
close all
%% 1. ENGINE RUNTIME

fileID=fopen('../out/8. OBD-II/1. EngineRunTime.txt','r');
formatSpec = '%s';
A = fscanf(fileID,formatSpec);
fclose(fileID);

n=1;
for i = 1:4:length(A)-3
   ERT(n) = hex2dec( A(i:i+1) ) * 256 + hex2dec( A(i+2:i+3) );
   n = n + 1;
end

clear A

%% 2. INTAKE AIR TEMPERATURE

fileID=fopen('../out/8. OBD-II/2. IntakeAirPressure.txt','r');
formatSpec = '%s';
A = fscanf(fileID,formatSpec);
fclose(fileID);

n=1;
for i = 1:2:length(A)-1
   IATEMP(n) = hex2dec( A(i:i+1) ) - 40;
   n = n + 1;
end

clear A

%% 3. THROTTLE POSITION

fileID=fopen('../out/8. OBD-II/3. ThrottlePosition.txt','r');
formatSpec = '%s';
A = fscanf(fileID,formatSpec);
fclose(fileID);

n=1;
for i = 1:2:length(A)-1
   TPOS(n) = hex2dec( A(i:i+1) ) * 100/255;
   n = n + 1;
end

clear A

%% 4. ENGINE RPM

fileID=fopen('../out/8. OBD-II/4. RPM.txt','r');
formatSpec = '%s';
A = fscanf(fileID,formatSpec);
fclose(fileID);

n=1;
for i = 1:4:length(A)-3
   RPM(n) = (hex2dec( A(i:i+1) ) * 256 + hex2dec( A(i+2:i+3) ))/4;
   n = n + 1;
end

clear A

%% 5. VEHICLE SPEED

fileID=fopen('../out/8. OBD-II/5. Speed.txt','r');
formatSpec = '%s';
A = fscanf(fileID,formatSpec);
fclose(fileID);

n=1;
for i = 1:2:length(A)-1
   SPEED(n) = hex2dec( A(i:i+1) );
   n = n + 1;
end

clear A

%% 6. COOLANT TEMPERATURE

fileID=fopen('../out/8. OBD-II/6. CarTemperature.txt','r');
formatSpec = '%s';
A = fscanf(fileID,formatSpec);
fclose(fileID);

n=1;
for i = 1:2:length(A)-1
   COOLTEMP(n) = hex2dec( A(i:i+1) ) - 40;
   n = n + 1;
end

clear A

%% PLOTTING

f = figure('Position', get(0, 'Screensize'));

subplot(3,2,1);
plot(ERT);
ylabel('Seconds (sec)')
xlabel('Time (ms)')
title('1. Egine Run Time')

subplot(3,2,2);
plot(IATEMP);
ylabel('Celsius Degrees (C)')
xlabel('Time (ms)')
title('2. Intake Air Temperature')

subplot(3,2,3);
plot(TPOS);
ylabel('Percentage (%)')
xlabel('Time (ms)')
title('3. Throttle Position')

subplot(3,2,4);
plot(RPM);
ylabel('RPM')
xlabel('Time (ms)')
title('4. Engine RPM')

subplot(3,2,5);
plot(SPEED);
ylabel('Km/h')
xlabel('Time (ms)')
title('5. Vehicle Speed')

subplot(3,2,6);
plot(COOLTEMP);
ylabel('Celsius Degrees (C)')
xlabel('Time (ms)')
title('6. Coolant Temperature')

sgtitle({'G21: Vehicle OBD-II Plots';'Date: 09-04-2019, Time: 17:37 - 19:37 , Vehicle OBD-II code: V5926'})

F = getframe(f);
imwrite(F.cdata, 'Plots/G21.png', 'png');

