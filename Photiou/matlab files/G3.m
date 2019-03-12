clear all
close all
x = fopen('./session1/echowithoutdelay/responseTime.txt','r');
A = fscanf(x,'%d');
fclose(x);
figure
plot(A);
title('G3')
xlabel('packet')
ylabel('response time (ms)')
mesitimi = mean (A)
diaspora = var(A)