% NAME:     Flionis Ioannis
% AEM:      8263
% CONTACT:  iflionis@auth.gr

clear all
close all
%% G19 & G20

fileID=fopen('../out/7. Ithaki Copter/copter175.txt','r');
formatSpec = '%d %d %d %f %f';
A = fscanf(fileID,formatSpec);
fclose(fileID);

fileID=fopen('../out/7. Ithaki Copter/copter225.txt','r');
formatSpec = '%d %d %d %f %f';
B = fscanf(fileID,formatSpec);
fclose(fileID);

n = 1;
for i = 1:5:length(A)-4
   RMA(n)  = A(i);	
   LMA(n)  = A(i+1);
   ALTA(n) = A(i+2);
   TEMA(n) = A(i+3);
   PREA(n) = A(i+4);
   n = n + 1;
end

%clear A

n = 1;
for i = 1:5:length(B)-4
   RMB(n)  = B(i);	
   LMB(n)  = B(i+1);
   ALTB(n) = B(i+2);
   TEMB(n) = B(i+3);
   PREB(n) = B(i+4);
   n = n + 1;
end

%clear B


%% G19

f1 = figure('Position', get(0, 'Screensize'));

subplot(3,2,1);
plot(LMA);
title('Copter Left Motor')
ylabel('RPM')
xlabel('Time (ms)')

subplot(3,2,2);
plot(RMA);
title('Copter Right Motor')
ylabel('RPM')
xlabel('Time (ms)')

subplot(3,2,3);
plot(ALTA);
title('Copter Altitude')
ylabel('Centimeters (cm)')
xlabel('Time (ms)')

subplot(3,2,4);
plot(TEMA);
title('Copter Temperature')
ylabel('Celsius Degrees (C)')
xlabel('Time (ms)')

subplot(3,2,5);
plot(PREA);
title('Copter Pressure')
ylabel('Pascal (Pa)')
xlabel('Time (ms)')

sgtitle({'G19: Ithaki Copter UDP Telemetry ( FLIGHTLEVEL=175 )';'Date: 12-04-2019, Time: 17:58 - 19:58, Ithakicopter code: Q3760'})

F = getframe(f1);
imwrite(F.cdata, 'Plots/G19.png', 'png');


%% G19

f2 = figure('Position', get(0, 'Screensize'));

subplot(3,2,1);
plot(LMB);
title('Copter Left Motor')
ylabel('RPM')
xlabel('Time (ms)')

subplot(3,2,2);
plot(RMB);
title('Copter Right Motor')
ylabel('RPM')
xlabel('Time (ms)')

subplot(3,2,3);
plot(ALTB);
title('Copter Altitude')
ylabel('Centimeters (cm)')
xlabel('Time (ms)')

subplot(3,2,4);
plot(TEMB);
title('Copter Temperature')
ylabel('Celsius Degrees (C)')
xlabel('Time (ms)')

subplot(3,2,5);
plot(PREB);
title('Copter Pressure')
ylabel('Pascal (Pa)')
xlabel('Time (ms)')

sgtitle({'G20: Ithaki Copter UDP Telemetry ( FLIGHTLEVEL=225 )';'Date: 12-04-2019, Time: 17:58 - 19:58, Ithakicopter code: Q3760'})

F = getframe(f2);
imwrite(F.cdata, 'Plots/G20.png', 'png');

