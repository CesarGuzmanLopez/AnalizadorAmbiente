#ifndef _HUMOGAS_H
#define _HUMOGAS_H
namespace HumoGasMQ2 {
class SensorHG {
    const int   resistenciaL = 5;
    const int   ResistenciaLimpio = 9.83;
    const float LPCurve[3] = {2.3, 0.21, -0.47} ;
    const float HumoCurve[3] = {2.3, 0.53, -0.44};
    float Ro =  10;
    int EntradaAnalogica; //entrada Analogica MQ2
  public:
    SensorHG(int EntradaAnalogica) {
      this->EntradaAnalogica = EntradaAnalogica;
      Calibracion();
    }
    float Calibracion() {
      int i;
      float val = 0;
      for (int i = 0; i < 50; i++) {                                                                         //tomar múltiples muestras
        val += calc_res(analogRead(EntradaAnalogica));
        delay(500);
      }
      val = val / 50;                                                                                       //calcular el valor medio
      Ro = val / ResistenciaLimpio;
    }
    float calc_res(int raw_adc)
    {
      return ( ((float)ResistenciaLimpio * (1023 - raw_adc) / raw_adc));
    }

    float lecturaMQ() {
      int i;
      float rs = 0;
      for (i = 0; i < 5; i++) {
        rs += calc_res(analogRead(EntradaAnalogica));
        delay(50);
      }
      rs = rs / 5;
      return rs;
    }
    int porcentaje_gas() {
      return (pow(10, (((log(lecturaMQ()) - LPCurve[1]) / LPCurve[2]) + LPCurve[0])));
    }
};
}
#endif
