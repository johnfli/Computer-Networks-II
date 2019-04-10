% NAME:     Flionis Ioannis
% AEM:      8263
% CONTACT:  iflionis@auth.gr

clear all
close all
%% G19 & G20

fileID=fopen('../out/7. Ithaki Copter/copterMatlab.txt','r');
formatSpec = '%d %d %d %f %f';
A = fscanf(fileID,formatSpec);
fclose(fileID);

n=1;
for i = 1:5:length(A)
   RM(n)  = A(i);
   LM(n)  = A(i+1);
   ALT(n) = A(i+2);
   TEM(n) = A(i+3);
   PRE(n) = A(i+4);
   n = n + 1;
end

%clear A

%% PLOTTING

f = figure('Position', get(0, 'Screensize'));

subplot(3,2,1);
plot(LM);
title('Copter Left Motor')
ylabel('RPM')
xlabel('Time (ms)')

subplot(3,2,2);
plot(RM);
title('Copter Right Motor')
ylabel('RPM')
xlabel('Time (ms)')

subplot(3,2,3);
plot(ALT);
title('Copter Altitude')
ylabel('Centimeters (cm)')
xlabel('Time (ms)')

subplot(3,2,4);
plot(TEM);
title('Copter Temperature')
ylabel('Celsius Degrees (C)')
xlabel('Time (ms)')

subplot(3,2,5);
plot(PRE);
title('Copter Pressure')
ylabel('Pascal (Pa)')
xlabel('Time (ms)')

sgtitle({'G19: Ithaki Copter UDP Telemetry';'Date: 09-04-2019, Time: 17:37 - 19:37, Ithakicopter code: Q3109'})

F = getframe(f);
imwrite(F.cdata, 'Plots/G19.png', 'png');

