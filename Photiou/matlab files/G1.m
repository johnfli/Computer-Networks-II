clear all
close all
x = fopen('./session1/echowithdelay/responseTime.txt','r');
A = fscanf(x,'%d');
fclose(x);
f1=figure;
plot(A);
title('G1')
xlabel('packet')
ylabel('response time (ms)')
mesitimi = mean (A)
diaspora = var(A)