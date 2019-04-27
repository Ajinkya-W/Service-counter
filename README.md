# Service-counter

There is a service counter which has a limited waiting queue outside it. <br\>It works as follows:
i)The counter remains open till the waiting queue is not empty
ii)If the queue is already full, the new customer simply leaves
iii)If the queue becomes empty, the outlet doors will be closed (service personel sleep)
iv)Whenever a customer arrives at the closed outlet, he/she needs to wake the person at the counter with a wake-up call

Implemented the above-described problem using semaphores or mutuexes along with threads. Also shown how it works, if there are 2 service personel, and a single queue. <br/>Tried to simulate all possible events that can take place, in the above scenario.
