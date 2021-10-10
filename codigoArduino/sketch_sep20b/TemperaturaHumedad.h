#ifndef _TemperaturaHumedad_H
#define _TemperaturaHumedad_H
#include "DHT.h"
namespace TemHumDHT11 {
  
  class TempHumo{

      DHT *dht;
      public:
      TempHumo(int Digitalport){
        dht = new DHT(Digitalport, DHT11);
        dht->begin();
      }
      float getTemperatura(){
        return dht->readTemperature();
      }
  };
}
#endif
