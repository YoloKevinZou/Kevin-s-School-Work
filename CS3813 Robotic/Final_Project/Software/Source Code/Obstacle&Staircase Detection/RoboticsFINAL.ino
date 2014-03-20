//http://www.arduino.cc/cgi-bin/yabb2/YaBB.pl?num=1236272388
#include <math.h>
int ultraSoundSignalPins[] = {7,8}; // Front Left,Front, Front Right, Rear Ultrasound signal pins
char *pingString[] = {"ping1:"," ping2:"}; // just something to print to indicate direction
int maximumRange = 500; // Maximum range needed
int minimumRange = 0; // Minimum range needed

const int  echoPin1 = 9; // Echo Pin
const int  trigPin1 = 10; // Trigger Pin
const int  echoPin2 = 11; // Echo Pin
const int  trigPin2 = 12; // Trigger Pin
const int buzzerPin = 6;
int tempIn = 3;       // temperature sensor on Analogue Pin 3
int ledOut = 7; 
float degC;            // The temperature in Degrees Centigrade
long dur1, dur2, dis1, dis2;

void setup()
{
   pinMode(ledOut, OUTPUT);    // Configure the Digital Pin Direction for the LED  
  Serial.begin(9600);
   pinMode(trigPin1, OUTPUT);
   pinMode(echoPin1, INPUT);
   pinMode(trigPin2, OUTPUT);
   pinMode(echoPin2, INPUT);
}

void loop(){  
   digitalWrite(trigPin1, LOW); 
   digitalWrite(trigPin2, LOW); 
   delayMicroseconds(100); 
   
   digitalWrite(trigPin1, HIGH);
   digitalWrite(trigPin2, HIGH);
   delayMicroseconds(200); 
   
   digitalWrite(trigPin1, LOW);
   dur1 = pulseIn(echoPin1, HIGH);
   dis1 = dur1/ 29 / 2;
   
   digitalWrite(trigPin2, LOW);
   dur2 = pulseIn(echoPin2, HIGH);
   dis2 = dur2/ 29 / 2;
   
   if (dis1 >= maximumRange || dis1 <= minimumRange){
     Serial.print("NO ");
   }
   else {
     Serial.print("a:"); //dis1
     Serial.print(dis1);
     Serial.print(" ");
   }
   
   if (dis2 >= maximumRange || dis2 <= minimumRange){
     Serial.print("NO ");
   }
   else {
     Serial.print(" b:"); //dis2
     Serial.print(dis2);
     Serial.print(" ");
   }
  
  if(checkStair(dis1, dis2)){
      tone(buzzerPin, 523, 50);
      delay(100);
      return;
  }
  
  else if(leg(dis1,dis2)){
    tone(buzzerPin, 60000, 50);
    delay(100);
    return;
  }
  
  unsigned long ultrasoundValue;
  for(int i=0; i < 2; i++){
    ultrasoundValue = ping(i);
     if (ultrasoundValue >= maximumRange || ultrasoundValue <= minimumRange){
        Serial.print(pingString[i]); 
        Serial.print("-1");
        //Serial.println();
     }
     else{
        Serial.print(pingString[i]);
        Serial.print(ultrasoundValue);   
        delay(100);
        if(ultrasoundValue<=100 && ultrasoundValue>=0){
          tone(buzzerPin, 10240, 10);
          
        }
     }
  }
  getTemperature(getVolts()); 
  Serial.println();
  delay(100);
 }

//Ping function
unsigned long ping(int i)
{
  unsigned long echo;

  pinMode(ultraSoundSignalPins[i], OUTPUT); // Switch signalpin to output
  digitalWrite(ultraSoundSignalPins[i], LOW); // Send low pulse
  delayMicroseconds(2); // Wait for 2 microseconds
  digitalWrite(ultraSoundSignalPins[i], HIGH); // Send high pulse
  delayMicroseconds(5); // Wait for 5 microseconds
  digitalWrite(ultraSoundSignalPins[i], LOW); // Holdoff
  pinMode(ultraSoundSignalPins[i], INPUT); // Switch signalpin to input
  digitalWrite(ultraSoundSignalPins[i], HIGH); // Turn on pullup resistor
  echo = pulseIn(ultraSoundSignalPins[i], HIGH); //Listen for echo
  return (echo/ 29 / 2); //convert to CM
}

boolean checkStair(long a, long b){
  
  long c;
  c = b-a;
   if(c<=35 && c>=25)
     return true;
   else
     return false;
}

boolean leg(long a, long b){
  
   if(abs(a-b)<5 && a < 100 && b < 100)
     return true;
   else
     return false;
}

float getVolts()
{
  int inputValue;
  inputValue = analogRead(tempIn);
  float volts;
  volts = (((float)inputValue / 1024) * 5);
  
//  Serial.print("Input Value: ") ; Serial.print(inputValue);
//  Serial.print(" | Voltage: ") ; Serial.print(volts);
  return volts;
}
/*
Return the temperature in DegreesC for given voltage
*/
float getTemperature(float volts)
{
  float temp = (volts - 0.5) / 0.01 ;
  float f = (temp*9/5)+32;
  Serial.print(" Temperature: "); Serial.print(temp); Serial.print(" 'C");
//   Serial.print(" | Temperature: "); Serial.print(f); Serial.print(" 'F");
  return temp;
}



