% NAME:     Flionis Ioannis
% AEM:      8263
% CONTACT:  iflionis@auth.gr

clear all
close all
%% ENGINE RUNTIME

fileID=fopen('../out/8. OBD-II/EngineRunTime.txt','r');
formatSpec = '%d';
ERT = fscanf(fileID,formatSpec);
fclose(fileID);

%% 1. INTAKE AIR TEMPERATURE

fileID=fopen('../out/8. OBD-II/1. IntakeAirPressure.txt','r');
formatSpec = '%d';
IATEMP = fscanf(fileID,formatSpec);
fclose(fileID);

%% 2. THROTTLE POSITION

fileID=fopen('../out/8. OBD-II/2. ThrottlePosition.txt','r');
formatSpec = '%d';
TPOS = fscanf(fileID,formatSpec);
fclose(fileID);

%% 3. ENGINE RPM

fileID=fopen('../out/8. OBD-II/3. RPM.txt','r');
formatSpec = '%d';
RPM = fscanf(fileID,formatSpec);
fclose(fileID);

%% 4. VEHICLE SPEED

fileID=fopen('../out/8. OBD-II/4. Speed.txt','r');
formatSpec = '%d';
SPEED = fscanf(fileID,formatSpec);
fclose(fileID);

%% 5. COOLANT TEMPERATURE

fileID=fopen('../out/8. OBD-II/5. CarTemperature.txt','r');
formatSpec = '%d';
COOLTEMP = fscanf(fileID,formatSpec);
fclose(fileID);

%% PLOTTING

f = figure('Position', get(0, 'Screensize'));

subplot(3,2,1);
plot(ERT, IATEMP);
ylabel('Celsius Degrees (C)')
xlabel('Time (sec)')
title('1. Intake Air Temperature')

subplot(3,2,2);
plot(ERT, TPOS);
ylabel('Percentage (%)')
xlabel('Time (sec)')
title('2. Throttle Position')

subplot(3,2,3);
plot(ERT, RPM);
ylabel('RPM')
xlabel('Time (sec)')
title('3. Engine RPM')

subplot(3,2,4);
plot(ERT, SPEED);
ylabel('Km/h')
xlabel('Time (sec)')
title('4. Vehicle Speed')

subplot(3,2,5);
plot(ERT, COOLTEMP);
ylabel('Celsius Degrees (C)')
xlabel('Time (sec)')
title('5. Coolant Temperature')

sgtitle({'Vehicle OBD-II Plots';'Date: 12-04-2019, Time: 17:58 - 19:58, Vehicle OBD-II code: V0975'})

F = getframe(f);
imwrite(F.cdata, 'Plots/G21.png', 'png');

