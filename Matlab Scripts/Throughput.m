function [ throughput ] = Throughput( response_times, num_of_secs )
    sum = 0;
    n = 0;
    j = 0;
    for i=1:length(response_times)
        sum = sum + response_times(i);
        n = n + 1;
        if (sum >= num_of_secs * 1000)
            j = j + 1;
            throughput(j) = n/num_of_secs;
            while (sum > (num_of_secs-1) * 1000)
                sum = sum - response_times(i-n+1);
                n = n - 1;
            end
        end
    end
end

