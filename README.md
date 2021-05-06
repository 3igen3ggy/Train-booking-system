# Train-booking-system
System of booking train tickets

I wrote this program for OOP training purpose.

Principle of operation:
1. We add cities with given name, latitude and longitue
2. We create stations from those cities (there could be may stations in one city)
3. We create trains with given names (IDs), given capacity per car and a number of cars
4. We create train lines, with specific route (List<Station>), assigned train and departure time (from first station)
5. We create some passenger accounts (for assigning some initital tickets), check Generator.class for ID/password pairs for login
6. The tickets are assigned a to given line, for specific passenger, from start station to destination station

When starting program we can:
-Create new account
-Use existing account (point 5.) to login. Use passenger ID and passenger password
-Get into admin mode (pass: 3igen3ggy), where we have overview of all the cities, stations, trains, lines, tickets, passengers

If we are logged in, we get into menu of buying tickets. We are asked for start station ID and destination station ID. If there exist lines that is connecting these two stations, 
they are listed and we are asked to choose which one is better for us.

We are given price and course details. If we approve, and there are remaining free places in a train, we get "printed" ticket to print.


To do in future:
-validate email
-password strength validation
-add more lines/trains/tickets
-manipulating system from admin panel
