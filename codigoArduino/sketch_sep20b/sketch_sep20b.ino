#include "DHT.h"

// Uncomment whatever type you're using!
//#define DHTTYPE DHT11   // DHT 11
#define DHTTYPE DHT22   // DHT 22  (AM2302), AM2321
//#define DHTTYPE DHT21   // DHT 21 (AM2301)

// Connect pin 1 (on the left) of the sensor to +5V
// NOTE: If using a board with 3.3V logic like an Arduino Due connect pin 1
// to 3.3V instead of 5V!
// Connect pin 2 of the sensor to whatever your DHTPIN is
// Connect pin 4 (on the right) of the sensor to GROUND
// Connect a 10K resistor from pin 2 (data) to pin 1 (power) of the sensor

const int DHTPin = 5;     // what digital pin we're connected to

DHT dht(DHTPin, DHTTYPE);
char option = ' ';
void setup() {
   delay(100);
   Serial.begin(9600);
   

   dht.begin();
}

void loop() {
    
   if(Serial.available() != 0){
    option = Serial.read();
    switch(option){
      case('t'):
          Serial.println("Temperatura:"+String(temperatura())+"\n");
          break;
      case('H'):
          Serial.println("Humedad:"+String(humedad())+"\n");
          break;
       case('p'):
          Serial.println("Polvo:"+String(polvo())+"\n");
          break;
       case('h'):
          Serial.println("Humo:"+String(humo())+"\n");
          break;
       case('\n'):
       case('\t'):
       case('\0'):
       case('.'):
            break;        
        default:
            Serial.println(option+ " error");
            break;
      }
   }
  
  delay(100);
}

float polvo(){
  return 1;
  }
float humedad(){
  return 2;
  }
float temperatura(){
  return 3;
  }
float humo(){
  return 4;
  }
