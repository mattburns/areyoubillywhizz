# areyoubillywhizz
Imported from code.google.com/p/areyoubillywhizz and https://sourceforge.net/projects/timeonyourhands/

I wrote this years ago and as such, the code is pretty crazy. It was very tied to Windows and IIRC had to do some crazy stuff to modify the JRE to get at the serial port. I strongly suggest starting again from scratch. 

There is a built windows installer in the build directory but I don't have a windows machine, or even a serial port, to test this. Can someone let me know if it works? :)

## Intention
Log and display times from your StackMat or Rubiks competition timer. Logs personal records and can display current time in glorious fullscreen. The display port on stackmat timers uses a headphone jack but actually communicates a serial RS232 protocol. I see I wrote a test case for the parsing of the codes out of the device: https://github.com/mattburns/areyoubillywhizz/blob/master/areyoubillywhizz/test/parser/TimerCodeParserTest.java

![display](images/display.jpg?raw=true "display")

![screenshot](images/screenshot.jpg?raw=true "screenshot")

## Cable
You'll probably need to make your own cable to connect your stackmat to your serial port. The cable you will need is 2.5mm jack to female D9. Pin 2 is on the tip of the jack, and pin 5 is the middle ring.

![cable](images/cable.jpg?raw=true "cable")

