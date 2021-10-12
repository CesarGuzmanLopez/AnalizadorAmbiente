#ifndef _POLVO_H
#define _POLVO_H
#include <SPI.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>

namespace POLVOgp2y {
class SensorPolvo {
    int Analogico,  digital;
  protected:
    float voMeasured = 0;
    float calcVoltage = 0;
    float dustDensity = 0;
  public:
    SensorPolvo(int Analogico, int digital) {
      this->Analogico = Analogico;
      this->digital = digital;
      pinMode(digital,OUTPUT); 
    }
    float getPolvo() {
      digitalWrite(digital, LOW);
      delayMicroseconds(280);

      voMeasured = analogRead(Analogico);

      delayMicroseconds(40);
      digitalWrite(digital, HIGH);
    //delayMicroseconds(sleepTime);
      calcVoltage = voMeasured * (5.0 / 1024);
      dustDensity = 0.17 * calcVoltage - 0.1;

      if ( dustDensity < 0)
      {
        dustDensity = 0.00;
      }
      return dustDensity;
    }
};


}
#endif
