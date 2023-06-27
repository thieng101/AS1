public class drat {

    

    //case 1: curNode is in three node
                if(curNode.isThreeNode()){

                    
                    if(curNode.value1 == value){
                        RP = curNode.centerChild;
                    }

                    if(curNode.value2 == value){
                        RP = curNode.rightChild;
                    }

                    while(true){

                        // if RP is two node
                        if(RP.isTwoNode()){
                            //fuse and rotate for the first time
                            if(RP == curNode.centerChild){
                                // System.out.println("RP is center child");
                                //rotate or fuse with left sibling only
                                if(curNode.leftChild.isTwoNode()){
                                    //fuse at the left child
                                    RP = fuse(RP.parent.leftChild);
                                    // System.out.println("after fuse value of RP is " + RP.value1 + RP.value2 + RP.value3);
                                    // System.out.println("value of the first child is " + RP.leftChild.value1);                                    System.out.println("value of the first child is " + RP.leftChild.value1);
                                    // System.out.println("value of the second child is " + RP.centerLeftChild.value1);
                                    // System.out.println("value of the third child is " + RP.centerRightChild.value1);
                                    // System.out.println("value of the four child is " + RP.rightChild.value1);
                                    // if(RP.leftChild.isLeaf) System.out.println("children is leaf");


                                }
                                
                                else if(!(curNode.leftChild.isTwoNode())){
                                    RP = rotate(RP.parent.leftChild);
                                }
                            }

                            else if(RP == curNode.rightChild){
                                if(curNode.centerChild == null) System.out.println("RP is null");
                                //rotate or fuse with center sibling only
                                if(curNode.centerChild.isTwoNode()){
                                    RP = fuse(RP);
                                }
                                else{
                                    RP = rotate(RP);
                                }
                            }

                            //fuse and rotate for the second time/ when going to left most
                            else if(RP != curNode.centerChild && RP != curNode.rightChild){ 
                                //get the sibling then check if sibling is two node
                                sibling = getSibling(RP);
                                if(sibling.isTwoNode()){
                                    RP = fuse(RP);
                                }

                                if(!(sibling.isTwoNode())){
                                    RP = rotate(RP);
                                }


                            }
                        }
                        
                        //if RP is leaf
                        if(RP.isLeaf){
                            //delete in the example delete 10
                            //there are two cases, left most node contain the value, and not contain the value
                            //case left most contains the value after rotating or fuse

                            //fuse at the left child(delete 10)
                            /**************************
                            *                 20      *
                            *   5   10    14       25 *
                            ***************************/

                            //rotate at the left child(delet 10)
                            /****************************
                            *      6         20         *   
                            *  5      10 14      25     *
                            *****************************/

                            //fuse at the right child(delete 20)
                            /*******************************************
                            *      10                                  *  
                            *    5    14  20  25                       *
                            ********************************************/

                            //rotate at the right child(delete 20)
                            /*******************************************
                            *      10     15                            *  
                            *    5    14      20|25                     *
                            ********************************************/

                            
                            if(RP.checkValueWithinTheNode(value)){
                                if(RP.value2 == value){
                                    // System.out.println("RP is at value 2");
                                    // System.out.println("value at RP is " +  RP.values);
                                    RP.value2 = RP.value3;
                                    RP.value3 = 0;
                                    RP.values--;
                                }

                                else if(RP.value1 == value){
                                    RP.value1 = RP.value2;
                                    RP.value2 = 0;
                                    RP.values--;
                                }
                            }

                            //delete 10
                            /**************************
                            *     10        20        *
                            *   5    13|14     25     *
                            ***************************/

                            //delete 20
                            /**************************
                            *     10      20          *
                            *   5     14     24|25    *
                            ***************************/

                            else if(!RP.checkValueWithinTheNode(value)){
                                //swap then delete
                                if(curNode.value1 == value)
                                    curNode.value1 = RP.value1;
                                
                                else if(curNode.value2 == value)
                                    curNode.value2 = RP.value1;

                                RP.value1 = RP.value2;
                                RP.value2 = RP.value3;
                                RP.value3 = 0;
                                RP.values--;
                            }
                            break;
                        }

                        //if RP is not two node and not leaf, then continue going to left most
                        RP = getImmediateRightChild(RP, value);
                        // System.out.println("RP now is " + RP.value1);
                        
                    }
                    
                }

                //case 2: curNode is in four node
                
                
                //case that rotate or fuse for the first time: 10 ends up in value 2 anyway
                //case that rotate or fuse for the second time: 20 ends up in value 2 anyway
                //case that rotate or fuse for the third time: 30 ends up in value 2 anyway

                else if(curNode.isFourNode()){
                    if(curNode.value1 == value){
                        RP = curNode.centerLeftChild;
                    }

                    else if(curNode.value2 == value){
                        RP = curNode.centerRightChild;
                    }

                    else if(curNode.value3 == value){
                        System.out.println("RP is at right child");
                        RP = curNode.rightChild;
                    }


                    while(true){
                        //if RP is two node
                        // if(RP != null) System.out.println("RP is not null");
                        // if(RP.isThreeNode()) System.out.println("RP is three node");
                        // if(RP.isFourNode()) System.out.println("RP is four node");

                        if(RP.isTwoNode()){
                            //fuse and rotate for the first time
                            if(RP == curNode.centerLeftChild){
                                //rotate or fuse with left sibling only
                                if(curNode.leftChild.isTwoNode()){
                                    //fuse at the left sibling
                                    RP = fuse(RP.parent.leftChild);
                                }

                                else if(!(curNode.leftChild.isTwoNode())){
                                    RP = rotate(RP.parent.leftChild);
                                }

                            }    
                            
                            else if(RP == curNode.centerRightChild){
                                //rotate or fuse with center left sibling only
                                if(curNode.centerLeftChild.isTwoNode()){
                                    //fuse at the left sibling
                                    RP = fuse(RP.parent.centerLeftChild);
                                }

                                else if(!(curNode.centerLeftChild.isTwoNode())){
                                    //rotate with left sibling
                                   prv2 = RP.parent.value2;
                                   RP.value2 = RP.value1;
                                   RP.value1 = prv2;
                                   if(RP.parent.centerLeftChild.isThreeNode()){
                                    RP.parent.value2 = RP.parent.centerLeftChild.value2;
                                   }
                                   else if(RP.parent.centerLeftChild.isFourNode()){
                                    RP.parent.value2 = RP.parent.centerLeftChild.value3;
                                   }
                                   RP.centerChild = RP.leftChild;
                                   RP.leftChild = RP.parent.centerLeftChild.rightChild;
                                   RP.leftChild.parent = RP;
                                   RP.values++;

                                   //Fix L values
                                   if(RP.parent.centerLeftChild.isThreeNode()){
                                    RP.parent.centerLeftChild.value2 = 0;
                                   }
                                   else if(RP.parent.centerLeftChild.isFourNode()){
                                    RP.parent.centerLeftChild.value3 = 0;
                                   }

                                   //Fix L ponters
                                   if(RP.parent.centerLeftChild.isThreeNode()){
                                    RP.parent.centerLeftChild.rightChild = RP.parent.centerLeftChild.centerChild;
                                    RP.parent.centerLeftChild.centerChild = null;
                                   }
                                   else if(RP.parent.centerLeftChild.isFourNode()){
                                    RP.parent.centerLeftChild.rightChild = RP.parent.centerLeftChild.centerRightChild;
                                    RP.parent.centerLeftChild.centerChild = RP.parent.centerLeftChild.centerLeftChild;
                                    RP.parent.centerLeftChild.centerLeftChild = null;
                                    RP.parent.centerLeftChild.centerRightChild = null;
                                   }

                                   RP.parent.centerLeftChild.values--;

                                }
                            }
                            
                            else if(RP == curNode.rightChild){
                                //rotate or fuse with center right sibling only
                                if(curNode.centerRightChild.isTwoNode()){
                                     
                                    RP = fuse(RP);
                                }

                                else if(!(curNode.centerLeftChild.isTwoNode())){
                                    RP = rotate(RP);
                                }
                            }
                            
                            //fuse and rotate when going to left most
                            else{
                                //get the sibling then check if sibling is two nodes
                                sibling = getSibling(RP);
                                if(sibling.isTwoNode()){
                                    RP = fuse(RP);
                                }
                                else{
                                    RP = rotate(RP);
                                }
                            }
                        
                        }

                        if(RP.isLeaf){
                            if(RP.checkValueWithinTheNode(value)){
                                if(RP.value2 == value){
                                    RP.value2 = RP.value3;
                                    RP.value3 = 0;
                                    RP.values--;
                                }

                                else if(RP.value1 == value){
                                    RP.value1 = RP.value2;
                                    RP.value2 = RP.value3;
                                    RP.value3 = 0;
                                    RP.values--;
                                }

                            }
                            else{
                                //swap then delete
                                if(curNode.value1 == value){
                                    curNode.value1 = RP.value1;
                                }
                                else if(curNode.value2 == value){
                                    curNode.value2 = RP.value1;
                                }
                                else if(curNode.value3 == value){
                                    curNode.value3 = RP.value1;
                                }

                                RP.value1 = RP.value2;
                                RP.value2 = RP.value3;
                                RP.value3 = 0;
                                RP.values--;
                            }
                            break;
                        }

                        RP = getImmediateRightChild(RP, value);


                    }
                }
            }


    
}
