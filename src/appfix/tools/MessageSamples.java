package appfix.tools;

public class MessageSamples {

	public static final String LOGON = "8=FIX.4.2|9=76|35=A|34=702|49=ABC|52=20100130-10:52:40.663|56=XYZ|95=4|96=1234|98=0|108=60|10=134|";
	public static final String HEART_BEAT = "8=FIX.4.2|9=51|35=0|34=703|49=ABC|52=20100130-10:53:40.830|56=XYZ|10=249|";
	public static final String NEW_ORDER_SINGLE = "8=FIX.4.4|9=122|35=D|34=215|49=CLIENT12|52=20100225-19:41:57.316|56=B|1=Marcel|11=13346|21=1|40=2|44=5|54=1|59=0|60=20100225-19:39:52.020|10=072|";
	public static final String NEW_ORDER_LIST = "8=FIX.4.29=11935=E66=59201b99-2f81-4be9-a334-14b89025fe9868=3394=173=211=OPTION-167=155=XLP54=211=OPTION-267=255=XLP54=210=164";
	public static final String EXECUTION_REPORT = "8=FIX.4.2|9=266|35=8|49=ABC|56=XYZ|50=AZ12|57=NA|34=833|52=20100130-08:00:51.992|55=GLD|48=PL11YA|167=FUT|207=LIFFE|1=AA1|37=ABC1|17=INDNTHDOG|58=Fill|200=201009|205=13|32=25|151=0|14=25|54=2|40=2|77=O|59=0|150=2|20=0|39=2|442=1|44=99.06|38=25|31=99.06|6=99.06|60=20100130-08:00:52|10=136|";
	public static final String MARKET_DATA_REQUEST = "8=FIX.4.2�9=166�35=V�34=2�49=REMOTE�52=20120409-15:47:17�56=TT_PRICE�146=2�55=6E�48=00A0FM006EZ�207=CME�55=ZT�48=00A0IM00ZTZ�207=CBOT�262=2�263=1�264=1�265=0�266=Y�267=2�269=0�269=1�10=041�";

}
