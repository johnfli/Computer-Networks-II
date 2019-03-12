%G1 & G2
figure 
h=histfit(B)
%delete(h(1)) %mono outline
title({'G6 : Histogram with normal density function';'fit for throughput with delay '})
xlabel('throughput')
ylabel('frequency of values')