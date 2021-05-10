# Train-booking-system
System of booking train tickets

I wrote this program for OOP training purpose.

Principle of operation:
1. We add cities with given name, latitude and longitude
2. We create stations with those cities (there could be many stations in one city)
3. We create trains with given names (IDs), given capacity per car and a number of cars
4. We create train lines, with specific route (a list of stations), assigned train and departure time (from first station)
5. We create some passenger accounts (for assigning some initital tickets), check Generator.class for ID/password pairs for login
6. The tickets are assigned to a given line, specific passenger, only between start and destination station

When starting program we can:
-Create new account
-Use existing account (point 5.) to login. Use passenger ID and passenger password
-Get into admin mode (pass: 3igen3ggy), where we have overview of all the cities, stations, trains, lines, tickets, passengers

If we are logged in, we get into menu of buying tickets. We are asked for start station ID and destination station ID. If there are lines that connect these two stations
they are listed and we are asked to choose which one is better for us.

We are given price and course details. If we approve, and there are remaining free places in a train, we get ticket printed in console.


To do in future:
-password strength validation
-add more lines/trains/tickets
-manipulating system from admin panel
-ticket
