clear all
close all
x = fopen('../out/1. Echo Packets/0. No Delay/responseTime.txt','r');
A = fscanf(x,'%d');
fclose(x);
figure
plot(A);
title('G3')
xlabel('packet')
ylabel('response time (ms)')
mesitimi = mean (A)
diaspora = var(A)