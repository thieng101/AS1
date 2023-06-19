import javax.swing.text.Style;

public class App {
    public static void main(String[] args) throws Exception {
        TwoFourTree tft = new TwoFourTree();

        
        
        if(tft.addValue(10)){
            System.out.println("Adding" + 10 +  "successfully");
        }

        if(tft.addValue(50)){
            System.out.println("Adding"+ 50 +"successfully");
        }

        
        
        if(tft.addValue(20)){
            System.out.println("Adding"+ 20 +"successfully");
        }   
        
        if(tft.addValue(40)){
            System.out.println("Adding"+ 40 +"successfully");
        }
        
        if(tft.addValue(15)){
            System.out.println("Adding"+ 15 +"successfully");
        }

        if(tft.addValue(16)){
            System.out.println("Adding"+ 16 +"successfully");
        }

        // if(tft.addValue(17)){
        //     System.out.println("Adding"+ 17 +"successfully");
        // }

        if(tft.addValue(51)){
            System.out.println("Adding"+ 51 +"successfully");
        }


        if(tft.addValue(52)){
            System.out.println("Adding"+ 52 +"successfully");
        }


        if(tft.addValue(41)){
            System.out.println("Adding"+ 51 +"successfully");
        }

        if(tft.addValue(42)){
            System.out.println("Adding"+ 42 +"successfully");
        }

        //case:three node center child
        // if(tft.addValue(43)){
        //     System.out.println("Adding"+ 43 +"successfully");
        // }

        //case: three node left child
        // if(tft.addValue(17)){
        //     System.out.println("Adding"+ 17 +"successfully");
        // }

        //case: three node right child
        if(tft.addValue(53)){
            System.out.println("Adding"+ 53 +"successfully");
        }
             
        if(tft.addValue(70)){
            System.out.println("Adding"+ 70 +"successfully");
        }

        if(tft.addValue(17)){
            System.out.println("Adding"+ 17 +"successfully");
        }

        if(tft.addValue(43)){
            System.out.println("Adding"+ 43 +"successfully");
        }


     

        //child cases
        //case 1: two node left child
        // if(tft.addValue(44)){
        //     System.out.println("Adding"+ 44 +"successfully");
        // }

        //case 2: two node right child
        if(tft.addValue(75)){
            System.out.println("Adding"+ 75 +"successfully");
        }

        if(tft.addValue(80)){
            System.out.println("Adding"+ 80 +"successfully");
        }

        if(tft.addValue(87)){
            System.out.println("Adding"+ 87 +"successfully");
        }

        if(tft.addValue(88)){
            System.out.println("Adding"+ 88 +"successfully");
        }

        if(tft.addValue(89)){
            System.out.println("Adding"+ 89 +"successfully");
        }

        if(tft.addValue(54)){
            System.out.println("Adding"+ 54 +"successfully");
        }

        if(tft.addValue(55)){
            System.out.println("Adding"+ 55 +"successfully");
        }
        
        if(tft.addValue(56)){
            System.out.println("Adding"+ 56 +"successfully");
        }

        if(tft.addValue(57)){
            System.out.println("Adding"+ 57 +"successfully");
        }

        if(tft.addValue(58)){
            System.out.println("Adding"+ 58 +"successfully");
        }

        // if(tft.addValue(59)){
        //     System.out.println("Adding"+ 59 +"successfully");
        // }

        //test care child: three node , right child
        if(tft.addValue(90)){
            System.out.println("Adding"+ 90 +"successfully");
        }

        if(tft.addValue(91)){
            System.out.println("Adding"+ 91 +"successfully");
        }

        if(tft.addValue(92)){
            System.out.println("Adding"+ 92 +"successfully");
        }

        if(tft.addValue(93)){
            System.out.println("Adding"+ 93 +"successfully");
        }

        if(tft.addValue(76)){
            System.out.println("Adding"+ 76 +"successfully");
        }

        System.out.println("root value 1 is " + tft.getValue1());        
        System.out.println("root value 2 is " + tft.getValue2());
        System.out.println("root value 3 is " + tft.getValue3());

       
        // tft.printInOrder();
        // tft.printRigthChildren();

        tft.printInOrder();
    }
}
