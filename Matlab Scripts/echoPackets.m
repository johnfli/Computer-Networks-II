% NAME:     Flionis Ioannis
% AEM:      8263
% CONTACT:  iflionis@auth.gr

clear all
close all
%% G1

fileID = fopen('../out/1. Echo Packets/1. With Delay/responseTime.txt','r');
A = fscanf(fileID,'%d');
fclose(fileID);

f1 = figure('Position', get(0, 'Screensize'));
plot(A);
title({'G1: Response Time per Packet (Echo with Delay)';'Date: 13-04-2019, Time: 13:06 - 15:06, Echo request code: E3285'})
xlabel('Packets')
ylabel('Response Time (ms)')

mean_value = mean (A)
variance = var(A)

F = getframe(f1);
imwrite(F.cdata, 'Plots/G1.png', 'png');


%% G2

TA8 = Throughput(A, 8);
TA16 = Throughput(A, 16);
TA32 = Throughput(A, 32);

f2 = figure('Position', get(0, 'Screensize'));

subplot(3,1,1);
plot(TA8);
title('8 secs MA Throughput')

subplot(3,1,2);
plot(TA16);
title('16 secs MA Throughput')

subplot(3,1,3);
plot(TA32);
title('32 secs MA Throughput')

sgtitle({'G2: MA Throughput (Echo with Delay)';'Date: 13-04-2019, Time: 13:06 - 15:06, Echo request code: E3285'})

F = getframe(f2);
imwrite(F.cdata, 'Plots/G2.png', 'png');


%% G3

fileID = fopen('../out/1. Echo Packets/0. No Delay/responseTime.txt','r');
B = fscanf(fileID,'%d');
fclose(fileID);

f3 = figure('Position', get(0, 'Screensize'));
plot(B);
title({'G3: Response Time per Packet (Echo with No Delay)';'Date: 13-04-2019, Time: 13:06 - 15:06, Echo request code: E3285'})
xlabel('Packets')
ylabel('Response Time (ms)')

mean_value = mean (B)
variance = var(B)

F = getframe(f3);
imwrite(F.cdata, 'Plots/G3.png', 'png');


%% G4

TB8 = Throughput(B, 8);
TB16 = Throughput(B, 16);
TB32 = Throughput(B, 32);

f4 = figure('Position', get(0, 'Screensize'));

subplot(3,1,1);
plot(TB8);
title('8 secs MA Throughput')

subplot(3,1,2);
plot(TB16);
title('16 secs MA Throughput')

subplot(3,1,3);
plot(TB32);
title('32 secs MA Throughput')

sgtitle({'G4: MA Throughput (Echo with No Delay)';'Date: 13-04-2019, Time: 13:06 - 15:06, Echo request code: E3285'})

F = getframe(f4);
imwrite(F.cdata, 'Plots/G4.png', 'png');


%% G5

f5 = figure('Position', get(0, 'Screensize'));

h = histfit(A);

%delete(h(1)) %mono outline
title({'G5: Histogram with Normal Density Function';'fit for Response Time with Delay ';'Date: 13-04-2019, Time: 13:06 - 15:06, Echo request code: E3285'})
ylabel('Frequency Of Values')
xlabel('Response Time (ms)')

F = getframe(f5);
imwrite(F.cdata, 'Plots/G5.png', 'png');


%% G6

f6 = figure('Position', get(0, 'Screensize'));

h = histfit(TA8);

%delete(h(1)) %mono outline
title({'G6: Histogram with Normal Density Function';'fit for Throughput with Delay ';'Date: 13-04-2019, Time: 13:06 - 15:06, Echo request code: E3285'})
ylabel('Frequency Of Values')
xlabel('Throughput')

F = getframe(f6);
imwrite(F.cdata, 'Plots/G6.png', 'png');


%% G7

f7 = figure('Position', get(0, 'Screensize'));

h = histfit(B);

%delete(h(1)) %mono outline
title({'G7: Histogram with Normal Density Function';'fit for Response Time with No Delay ';'Date: 13-04-2019, Time: 13:06 - 15:06, Echo request code: E3285'})
ylabel('Frequency Of Values')
xlabel('Response Time (ms)')

F = getframe(f7);
imwrite(F.cdata, 'Plots/G7.png', 'png');


%% G8

f8 = figure('Position', get(0, 'Screensize'));

h = histfit(TB8);

%delete(h(1))%mono outline
title({'G8: Histogram with Normal Density Function';'fit for Throughput with No Delay ';'Date: 13-04-2019, Time: 13:06 - 15:06, Echo request code: E3285'})
ylabel('Frequency Of Values')
xlabel('Throughput')

F = getframe(f8);
imwrite(F.cdata, 'Plots/G8.png', 'png');

