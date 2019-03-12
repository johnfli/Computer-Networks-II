
%run G1 first
figure 
h= histfit(A)
%delete(h(1)) %mono outline
title({'G5 : Histogram with normal density function';'fit for response time with delay '})
xlabel('response time (ms)')
ylabel('frequency of values')