%G3 & G4
figure 
h=histfit(B)
%delete(h(1))%mono outline
title({'G8 : Histogram with normal density function';'fit for throughput with no delay '})
xlabel('throughput')
ylabel('frequency of values')