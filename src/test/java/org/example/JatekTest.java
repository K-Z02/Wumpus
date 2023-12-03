//package org.example;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.URL;
//
//public class JatekTest {
//    private static final Hos hos = new Hos(1,Irany.N,1);
//    private static final Labirintus labirintus = new Labirintus();
//    private static final FelhaszNev felhaszNev = new FelhaszNev();
//    private static Jatek jatek;
//    @BeforeAll
//    public static void setUp() throws URISyntaxException {
//        URI res = JatekTest.class.getClassLoader().getResource("wumpuszinput.txt").toURI();//nem mukodik a Jar-ban
//        File file = new File(res);
//        labirintus.palyaBeolvasas(file);
//        jatek = new Jatek(hos,labirintus,felhaszNev);
//    }
//    @Test
//    public void testPlay(){
//
//        jatek.play();
//        System.out.println("1");
////        String input = "1";
////        System.setIn(new ByteArrayInputStream(input.getBytes()));
////        Jatek asker = Mockito.mock(Jatek.class);
////        Mockito.when(asker.play()).then(1);
//
//    }
//}
