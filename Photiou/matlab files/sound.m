clear all
close all

%% Partial Waveforms
file=fopen('./session1/dpcm/SamplesDPCM.txt','r');
Sdpcm=fscanf(file,'%f');
f1=figure;
plot(Sdpcm);
title('G9 DPCM Partial Waveform');
xlabel('Number of Packets');

file=fopen('./session1/adpcm/SamplesAQDPCM.txt','r');
Saq=fscanf(file,'%f');
f2=figure;
plot(Saq);
title('G10 AQ-DPCM Partial Waveform');
xlabel('Number of Packets');

%%Distributions
file=fopen('./session1/dpcm/SamplesDiffsDPCM.txt','r');
Dif_dpcm=fscanf(file,'%f');
f3=figure;
histfit(Dif_dpcm);
title('G11 DPCM Distribution Differences');

file=fopen('./session1/adpcm/SamplesDiffsAQDPCM.txt','r');
Dif_aq=fscanf(file,'%f');
f4=figure;
histfit(Dif_aq);
title('G13 AQ-DPCM Distribution Differences');

f5=figure;
histfit(Sdpcm);
title('G12 DPCM Distribution Values');


f6=figure;
histfit(Saq);
title('G14 AQ-DPCM Distribution Values');

%% means Steps
file=fopen('./session1/adpcm/means.txt','r');
mean=fscanf(file,'%f');
f7=figure;
plot(mean);
title('G17 Samples Mean Values of Audio Clip 2');


file=fopen('./session1/adpcm/steps.txt','r');
step=fscanf(file,'%f');
f8=figure;
plot(step);
title('G18 Samples Step Values of Audio Clip 2');