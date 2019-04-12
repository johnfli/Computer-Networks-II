% NAME:     Flionis Ioannis
% AEM:      8263
% CONTACT:  iflionis@auth.gr

clear all
close all
%% Partial Waveforms

fileID = fopen('../out/4. Virtual Frequency Generator/Frequencies.txt','r');
freqs = fscanf(fileID,'%f');
fclose(fileID);

fileID = fopen('../out/5. DPCM Sound/SamplesDPCM.txt','r');
Sdpcm = fscanf(fileID,'%f');
fclose(fileID);

fileID = fopen('../out/6. AQ-DPCM Sound/SamplesAQDPCM1.txt','r');
Saq = fscanf(fileID,'%f');
fclose(fileID);

f1 = figure('Position', get(0, 'Screensize'));
plot(freqs);
title({'G9: Virtual Frequency Generator Partial Waveform';'Date: 12-04-2019, Time: 17:58 - 19:58, Audio request code: A1095'});
xlabel('Number of Packets');

F = getframe(f1);
imwrite(F.cdata, 'Plots/G9.png', 'png')

f2 = figure('Position', get(0, 'Screensize'));

subplot(2,1,1);
plot(Sdpcm);
title('DPCM Partial Waveform');
xlabel('Number of Packets');

subplot(2,1,2);
plot(Saq);
title('AQ-DPCM Partial Waveform');
xlabel('Number of Packets');

sgtitle({'G10: Partial Waveforms';'Date: 12-04-2019, Time: 17:58 - 19:58, Audio request code: A1095'})

F = getframe(f2);
imwrite(F.cdata, 'Plots/G10.png', 'png')


%% Distributions

fileID = fopen('../out/5. DPCM Sound/SamplesDiffsDPCM.txt','r');
Dif_dpcm = fscanf(fileID,'%f');
fclose(fileID);

fileID = fopen('../out/6. AQ-DPCM Sound/SamplesDiffsAQDPCM1.txt','r');
Dif_aq = fscanf(fileID,'%f');
fclose(fileID);


f3 = figure('Position', get(0, 'Screensize'));
histfit(Dif_dpcm);
title({'G11: DPCM Distribution Differences';'Date: 12-04-2019, Time: 17:58 - 19:58, Audio request code: A1095'});
F = getframe(f3);
imwrite(F.cdata, 'Plots/G11.png', 'png');

f4 = figure('Position', get(0, 'Screensize'));
histfit(Sdpcm);
title({'G12: DPCM Distribution Values';'Date: 12-04-2019, Time: 17:58 - 19:58, Audio request code: A1095'});
F = getframe(f4);
imwrite(F.cdata, 'Plots/G12.png', 'png');


f5 = figure('Position', get(0, 'Screensize'));
histfit(Dif_aq);
title({'G13: AQ-DPCM Distribution Differences';'Date: 12-04-2019, Time: 17:58 - 19:58, Audio request code: A1095'});
F = getframe(f5);
imwrite(F.cdata, 'Plots/G13.png', 'png');

f6 = figure('Position', get(0, 'Screensize'));
histfit(Saq);
title({'G14: AQ-DPCM Distribution Values';'Date: 12-04-2019, Time: 17:58 - 19:58, Audio request code: A1095'});
F = getframe(f6);
imwrite(F.cdata, 'Plots/G14.png', 'png');


%% Means and Steps

% AUDIO CLIP 1
fileID = fopen('../out/6. AQ-DPCM Sound/means1.txt','r');
mean = fscanf(fileID,'%f');
fclose(fileID);

f7 = figure('Position', get(0, 'Screensize'));
plot(mean);
title({'G15: Samples Mean Values of Audio Clip 1';'Date: 12-04-2019, Time: 17:58 - 19:58, Audio request code: A1095'});
F = getframe(f7);
imwrite(F.cdata, 'Plots/G15.png', 'png');

fileID = fopen('../out/6. AQ-DPCM Sound/steps1.txt','r');
step = fscanf(fileID,'%f');
fclose(fileID);

f8 = figure('Position', get(0, 'Screensize'));
plot(step);
title({'G16: Samples Step Values of Audio Clip 1';'Date: 12-04-2019, Time: 17:58 - 19:58, Audio request code: A1095'});
F = getframe(f8);
imwrite(F.cdata, 'Plots/G16.png', 'png');


% AUDIO CLIP 2
fileID = fopen('../out/6. AQ-DPCM Sound/means2.txt','r');
mean = fscanf(fileID,'%f');
fclose(fileID);

f9 = figure('Position', get(0, 'Screensize'));
plot(mean);
title({'G17: Samples Mean Values of Audio Clip 2';'Date: 12-04-2019, Time: 17:58 - 19:58, Audio request code: A1095'});
F = getframe(f9);
imwrite(F.cdata, 'Plots/G17.png', 'png');

fileID = fopen('../out/6. AQ-DPCM Sound/steps2.txt','r');
step = fscanf(fileID,'%f');
fclose(fileID);

f10 = figure('Position', get(0, 'Screensize'));
plot(step);
title({'G18: Samples Step Values of Audio Clip 2';'Date: 12-04-2019, Time: 17:58 - 19:58, Audio request code: A1095'});
F = getframe(f10);
imwrite(F.cdata, 'Plots/G18.png', 'png');

