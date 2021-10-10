#include "HumoGas.h"
#include "TemperaturaHumedad.h"

HumoGasMQ2::SensorHG *Sensorhg;
TemHumDHT11::TempHumo *SensorDHT;
void setup() {
  delay(100);
  Serial.begin(9600);
  Sensorhg =new HumoGasMQ2::SensorHG(0);
  SensorDHT=new TemHumDHT11::TempHumo(2);
  
}
void loop() {
  char option = '\0';
  
  if (Serial.available() != 0) {
    option = Serial.read();
    switch (option) {
      case ('t'):
        Serial.println("Temperatura:" + String(temperatura()) + "\n");
        break;
      case ('H'):
        Serial.println("Humedad:" + String(humedad()) + "\n");
        break;
      case ('p'):
        Serial.println("Polvo:" + String(polvo()) + "\n");
        break;
      case ('h'):
        Serial.println("Humo:" + String(humo()) + "\n");
        break;
      case ('\n'):
      case ('\t'):
      case ('\0'):
      case ('.'):
        break;
      default:
        Serial.println(option + " error");
        break;
    }
  }

  delay(100);
}

inline float polvo() {
  return 4;
}
float humedad() {
   return SensorDHT->getHumedad();
}
float temperatura() {
  return SensorDHT->getTemperatura();
}
float humo() {
  return Sensorhg->porcentaje_gas();
}
