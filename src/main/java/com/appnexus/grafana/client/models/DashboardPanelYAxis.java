/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(fluent = true)
public class DashboardPanelYAxis extends FlexibleSchemaComponent {
  private Format format;
  private String label;
  private Integer logBase;
  private Integer max;
  private Integer min;
  private Boolean show;

  public enum Format {
    NONE("none"),
    SHORT("short"),
    PERCENT("percent"),
    PERCENTUNIT("percentunit"),
    HUMIDITY("humidity"),
    PPM("ppm"),
    DB("dB"),
    HEX0X("hex0x"),
    HEX("hex"),
    SCI("sci"),
    LOCALE("locale"),
    CURRENCYUSD("currencyUSD"),
    CURRENCYGBP("currencyGBP"),
    CURRENCYEUR("currencyEUR"),
    CURRENCYJPY("currencyJPY"),
    CURRENCYRUB("currencyRUB"),
    CURRENCYUAH("currencyUAH"),
    CURRENCYBRL("currencyBRL"),
    CURRENCYDKK("currencyDKK"),
    CURRENCYISK("currencyISK"),
    CURRENCYNOK("currencyNOK"),
    CURRENCYSEK("currencySEK"),
    HERTZ("hertz"),
    NS("ns"),
    MIRCROSECONDS("\u00B5s"),
    MS("ms"),
    S("s"),
    M("m"),
    H("h"),
    D("d"),
    DTDURATIONMS("dtdurationms"),
    DTDURATIONS("dtdurations"),
    DATETIMEASISO("dateTimeAsIso"),
    DATETIMEASUS("dateTimeAsUS"),
    DATETIMEFROMNOW("dateTimeFromNow"),
    BITS("bits"),
    BYTES("bytes"),
    KBYTES("kbytes"),
    MBYTES("mbytes"),
    GBYTES("gbytes"),
    DECBITS("decbits"),
    DECBYTES("decbytes"),
    DECKBYTES("deckbytes"),
    DECMBYTES("decmbytes"),
    DECGBYTES("decgbytes"),
    PPS("pps"),
    BITS_PS("bps"),
    BYTES_PS("Bps"),
    KBITS("Kbits"),
    KBS("KBs"),
    MBITS("Mbits"),
    MBS("MBs"),
    GBS("GBs"),
    GBITS("Gbits"),
    OPS("ops"),
    RPS("rps"),
    WPS("wps"),
    IOPS("iops"),
    OPM("opm"),
    RPM("rpm"),
    WPM("wpm"),
    LENGTHMM("lengthmm"),
    LENGTHM("lengthm"),
    LENGTHFT("lengthft"),
    LENGTHKM("lengthkm"),
    LENGTHMI("lengthmi"),
    AREAM2("areaM2"),
    AREAF2("areaF2"),
    AREAMI2("areaMI2"),
    MASSMG("massmg"),
    MASSG("massg"),
    MASSKG("masskg"),
    MASST("masst"),
    VELOCITYMS("velocityms"),
    VELOCITYKMH("velocitykmh"),
    VELOCITYMPH("velocitymph"),
    VELOCITYKNOT("velocityknot"),
    MLITRE("mlitre"),
    LITRE("litre"),
    M3("m3"),
    DM3("dm3"),
    GALLONS("gallons"),
    WATT("watt"),
    KWATT("kwatt"),
    MWATT("mwatt"),
    VOLTAMP("voltamp"),
    KVOLTAMP("kvoltamp"),
    VOLTAMPREACT("voltampreact"),
    KVOLTAMPREACT("kvoltampreact"),
    WATTH("watth"),
    KWATTH("kwatth"),
    KWATTM("kwattm"),
    JOULE("joule"),
    EV("ev"),
    AMP("amp"),
    KAMP("kamp"),
    MAMP("mamp"),
    VOLT("volt"),
    KVOLT("kvolt"),
    MVOLT("mvolt"),
    DBM("dBm"),
    OHM("ohm"),
    LUMENS("lumens"),
    CELSIUS("celsius"),
    FARENHEIT("farenheit"),
    KELVIN("kelvin"),
    PRESSUREMBAR("pressurembar"),
    PRESSUREBAR("pressurebar"),
    PRESSUREKBAR("pressurekbar"),
    PRESSUREHPA("pressurehpa"),
    PRESSUREHG("pressurehg"),
    PRESSUREPSI("pressurepsi"),
    FORCENM("forceNm"),
    FORCEKNM("forcekNm"),
    FORCEN("forceN"),
    FORCEKN("forcekN"),
    FLOWGPM("flowgpm"),
    FLOWCMS("flowcms"),
    FLOWCFS("flowcfs"),
    FLOWCFM("flowcfm"),
    DEGREE("degree"),
    RADIAN("radian"),
    GRAD("grad"),
    ACCMS2("accMS2"),
    ACCFS2("accFS2"),
    ACCG("accG");

    private final String value;

    Format(String s) {
      value = s;
    }

    @JsonValue
    public String value() {
      return value;
    }
  }
}
