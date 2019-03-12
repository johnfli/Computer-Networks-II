clear all
close all

%% Partial Waveforms
file=fopen('../out/4. DPCM Sound/SamplesDPCM.txt','r');
Sdpcm=fscanf(file,'%f');
f1=figure;
plot(Sdpcm);
title('G9 DPCM Partial Waveform');
xlabel('Number of Packets');

file=fopen('../out/5. AQ-DPCM Sound/SamplesAQDPCM.txt','r');
Saq=fscanf(file,'%f');
f2=figure;
plot(Saq);
title('G10 AQ-DPCM Partial Waveform');
xlabel('Number of Packets');

%%Distributions
file=fopen('../out/4. DPCM Sound/SamplesDiffsDPCM.txt','r');
Dif_dpcm=fscanf(file,'%f');
f3=figure;
histfit(Dif_dpcm);
title('G11 DPCM Distribution Differences');

file=fopen('../out/5. AQ-DPCM Sound/SamplesDiffsAQDPCM.txt','r');
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
file=fopen('../out/5. AQ-DPCM Sound/means.txt','r');
mean=fscanf(file,'%f');
f7=figure;
plot(mean);
title('G17 Samples Mean Values of Audio Clip 2');


file=fopen('../out/5. AQ-DPCM Sound/steps.txt','r');
step=fscanf(file,'%f');
f8=figure;
plot(step);
title('G18 Samples Step Values of Audio Clip 2');