%run G3 first
figure 
h=histfit(A)
%delete(h(1)) %mono outline
title({'G7 : Histogram with normal density function';'fit for response time with no delay '})
xlabel('response time (ms)')
ylabel('frequency of values')