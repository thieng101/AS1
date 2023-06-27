
public class TwoFourTree {

    private class TwoFourTreeItem {

        int values = 1; 
        int value1 = 0; // always exists.
        int value2 = 0; // exists iff the node is a 3-node or 4-node
        int value3 = 0; // exists iff the node is 4-node

        boolean isLeaf = true;

        TwoFourTreeItem parent = null; // parent exists off the node is not root
        TwoFourTreeItem leftChild = null; // left child and right child exists off the node is a non-leaf 2-node
        TwoFourTreeItem rightChild = null;
        TwoFourTreeItem centerChild = null; // center child exists off the node is a non-leaf 3-node
        TwoFourTreeItem centerLeftChild = null; // center-left and center-right child exists off the node is a non-leaf 4-node     
        TwoFourTreeItem centerRightChild = null;

        // it is two node check if values == 1
        public boolean isTwoNode() {
            return values == 1;
        }

        // it is three node check if values == 2
        public boolean isThreeNode() {
            return values == 2;
        }

        // it is four node check if values == 3
        public boolean isFourNode() {
            return values == 3;
        }

        public boolean isRoot() {
            return parent == null;
        }

        public TwoFourTreeItem(int value1) {
            this.value1 = value1;
        }

        public TwoFourTreeItem(int value1, int value2) {
            this.value1 = value1;
            this.value2 = value2;
            this.values = 2;

        }

        public TwoFourTreeItem(int value1, int value2, int values3) {
            this.value1 = value1;
            this.value2 = value2;
            this.value3 = values3;
            this.values = 3;

        }

        private void printIndents(int indent) {
            for (int i = 0; i < indent; i++)
                System.out.printf(" ");
        }

        public void printInOrder(int indent) {
            if(!isLeaf) leftChild.printInOrder(indent + 1);
            printIndents(indent);
            System.out.printf("%d\n", value1);
            if(isThreeNode()) {
            if(!isLeaf) centerChild.printInOrder(indent + 1);
            printIndents(indent);
            System.out.printf("%d\n", value2);
            } else if(isFourNode()) {
            if(!isLeaf) centerLeftChild.printInOrder(indent + 1);
            printIndents(indent);
            System.out.printf("%d\n", value2);
            if(!isLeaf) centerRightChild.printInOrder(indent + 1);
            printIndents(indent);
            System.out.printf("%d\n", value3);
            }
            if(!isLeaf) rightChild.printInOrder(indent + 1);
        }

        //update leaf status 
        public boolean hasChildren(){
            return leftChild != null || rightChild != null || centerLeftChild != null || centerRightChild != null;
                
        }



        public boolean insertItem(int value) {

            // continuing the insertion
            if (values == 1) {              // insert at two nodes
                if (value < value1) {       // value is smaller than the first value
                    value2 = value1;
                    value1 = value;
                    // System.out.println("value1 of right child: " + value1);
                    // System.out.println("value1 of right child: " + value2);

                }
                else{
                value2 = value;             // value if bigger than the first value
                }

            } else if (values == 2) { // insert at three nodes
                if (value < value1) { // value is the smallest
                    value3 = value2;
                    value2 = value1;
                    value1 = value;
                } else if (value1 < value && value < value2) { // value is in the middle
                    value3 = value2;
                    value2 = value;
                } else {
                    value3 = value; // value is the biggest
                }
            }

            // update number of values after inserting
            values++;

            return true;
        }

        // FIXME
        // check if a value is in the tree already
        // return true if it is, else return false
        public boolean checkValue(int value){
            if((value1 == value) || (value2 == value) || (value3 == value))
                return true; 

            else if(values == 1){
                if(value < value1){
                    if(leftChild == null)
                        return false; 

                    return leftChild.checkValue(value);
                }
                else if(value > value1){
                    if(rightChild == null)
                        return false; 

                    return rightChild.checkValue(value);
                }
            }

            else if(values == 2){
                if(value < value1){
                    if(leftChild == null)
                        return false; 

                    return leftChild.checkValue(value);
                }
                else if(value > value2){
                    if(rightChild == null)
                        return false; 

                    return rightChild.checkValue(value);
                }
                else{
                    if(centerChild == null)
                        return false; 

                    return centerChild.checkValue(value);
                }
            }

            else if(values == 3){
                if(value < value1){
                    if(leftChild == null)
                        return false; 

                    return leftChild.checkValue(value);
                }
                else if(value > value3){
                    if(rightChild == null)
                        return false; 

                    return rightChild.checkValue(value);
                }
                else if(value < value2){
                    if(centerLeftChild == null)
                        return false; 

                    return centerLeftChild.checkValue(value);
                }
                else if(value > value2){
                    if(centerRightChild == null)
                        return false; 

                    return centerRightChild.checkValue(value);
                }
            }

            // default 
            return false; 
        }


        public TwoFourTreeItem searchValue(int value){
                if((value1 == value) || (  value2 == value) || ( value3 == value))
                    return  root;

                else if(values == 1){
                    if(value < value1){
                        if(leftChild == null)
                            return null; 

                        return leftChild.searchValue(value);
                    }
                    else if(value > value1){
                        if(rightChild == null)
                            return null; 

                        return rightChild.searchValue(value);
                    }
                }

                else if(values == 2){
                    if(value < value1){
                        if(leftChild == null)
                            return null; 

                        return leftChild.searchValue(value);
                    }
                    else if(value > value2){
                        if(rightChild == null)
                            return null; 

                        return rightChild.searchValue(value);
                    }
                    else{
                        if(centerChild == null)
                            return null; 

                        return centerChild.searchValue(value);
                    }
                }

                else if(values == 3){
                    if(value < value1){
                        if(leftChild == null)
                            return null; 

                        return leftChild.searchValue(value);
                    }
                    else if(value > value3){
                        if(rightChild == null)
                            return null; 

                        return rightChild.searchValue(value);
                    }
                    else if(value <    value2){
                        if(centerLeftChild == null)
                            return null; 

                        return centerLeftChild.searchValue(value);
                    }
                    else if(value >    value2){
                        if(centerRightChild == null)
                            return null; 

                        return centerRightChild.searchValue(value);
                    }
                }

                // default 
                return null; 
        }
        
        //this is used when root has three keys
        //also it is used when remove a value in a node that has more than one key
        public boolean directDeleteAtLeafNode(int value){
            if(values == 1){
                    value1 = 0;
                    root = null;
                    return true;
                }
            else if(values == 2){
                if(value1 == value){
                    value1 = value2;
                }                        
                
                value2 = 0;
                values = 1;
                //delete successfully
                return true;

            }
            else{                                           //values == 3
                if(value1 == value){
                    value1 = value2;
                    value2 = value3;
                    value3 = 0;
                    
                }else if(value2 == value){
                    value2 = value3;
                    value3 = 0;
                }else{
                    value3 = 0;
                }  
                values = 2;
                //delete successfully
                return true;

            }
        }
        
        public void removeValueInNodeHasMoreThanOneKey(int value){
            if(values == 2){
                if(value1 == value){
                    value1 = value2;
                }                        
                    
                value2 = 0;
                values = 1;
               
            }
            else{                                           //values == 3
                if(value1 == value){
                    value1 = value2;
                    value2 = value3;
                    value3 = 0;
                    
                }else if(value2 == value){
                    value2 = value3;
                    value3 = 0;
                }else{
                    value3 = 0;
                }  
                values = 2;
                

            }
        }
       
        public boolean checkValueWithinTheNode(int value){
            return value1 == value || value2 == value || value3 == value;
        }
    }


    TwoFourTreeItem root = null;

    public boolean addValue(int value){
        
        //add at root
        if (root == null) {
            root = new TwoFourTreeItem(value);
            // System.out.println("Adding at root");
            // System.out.println("First Value:" + root.value1);
            return true;
        }
        
        //check value before add
        if(root.checkValue(value)){
            return false;
        }
        
        //add
        TwoFourTreeItem curNode = root;
        while(true){
                if(curNode.isFourNode()) //if node is full
                {
                //split and return the parent node
                curNode = split(curNode);
                // System.out.println("value at the parent after splitting " + curNode.value1);
                // System.out.println("value at the parent after splitting " + curNode.value2);
                // System.out.println("value at the parent after splitting " + curNode.value3);


                //search from the parent node and return next node
                curNode = getNextChild(curNode,value);
                }
                else if(curNode.isLeaf){
                // System.out.println("found the leaf and break the loop");
                break;
                }
                else{ 
                    curNode = getNextChild(curNode,value);
                }
                
            
            }

        //find a leaf and insert
        // System.out.println("insert at node has   " + curNode.value1 + " " + curNode.value2);
        return curNode.insertItem(value);
        
      }


    public TwoFourTreeItem split(TwoFourTreeItem thisNode) {

        //variable to save the children of current node
        TwoFourTreeItem child1 = null;
        TwoFourTreeItem child2 = null;
        TwoFourTreeItem child3 = null;
        TwoFourTreeItem child4 = null;

        // if the parent is null
        if (thisNode.parent == null) {
            // System.out.println("Split at the root of the tree");
            //save value at root
            
            int savedVal1 = thisNode.value1;
            int savedVal2 = thisNode.value2;
            int savedVal3 = thisNode.value3;
 

            if(!root.isLeaf){
                // System.out.println("Split at root when root has chilred");
                 child1 = thisNode.leftChild;
                 child2 = thisNode.centerLeftChild;
                 child3 = thisNode.centerRightChild;
                 child4 = thisNode.rightChild;
            }
            

            thisNode.value1 = savedVal2;
            thisNode.value2 = 0;
            thisNode.value3 = 0;
            thisNode.values = 1;

            //modify the structure
            thisNode.leftChild = new TwoFourTreeItem(savedVal1);
            thisNode.rightChild = new TwoFourTreeItem(savedVal3);

            thisNode.leftChild.parent = thisNode;
            thisNode.rightChild.parent = thisNode;

            // reconncet root to its children if it has children
            if(!root.isLeaf){

                thisNode.leftChild.leftChild = child1;
                thisNode.leftChild.rightChild = child2;

                thisNode.rightChild.leftChild = child3;
                thisNode.rightChild.rightChild = child4;

                // update the parent status
                thisNode.leftChild.leftChild.parent = thisNode.leftChild;
                thisNode.leftChild.rightChild.parent = thisNode.leftChild;

                thisNode.rightChild.leftChild.parent = thisNode.rightChild;
                thisNode.rightChild.rightChild.parent = thisNode.rightChild;
                
                thisNode.leftChild.isLeaf = false;
                thisNode.rightChild.isLeaf = false;
            }    

            //update leaf status
            thisNode.isLeaf = false;

            // System.out.println("This node value is: " + thisNode.value1);
            return thisNode;

        }
        // if parents exists
        else {
            // saved the parents node for the ease of use
            // TwoFourTreeItem parent = thisNode.parent;

            // System.out.println("Split when parent exists");

            // if parent is two node tree, has one key
            if (thisNode.parent.isTwoNode()) {
                // System.out.println("split at two node");

                int middlValue = thisNode.value2;
                // System.out.println("Valuat at this node is: " + middlValue);
                // System.out.println("Valuat at this parent node is: " + thisNode.parent.value1);


                //case 1: current node is left child
                if(middlValue < thisNode.parent.value1){
                    // System.out.println("split at the left child of two node");
                    //update value in the root
                    thisNode.parent.value2 = thisNode.parent.value1;
                    thisNode.parent.value1 = middlValue;
                    
                    //check if current node has children
                    if(!thisNode.isLeaf){
                        child1 = thisNode.leftChild;
                        child2 = thisNode.centerLeftChild;
                        child3 = thisNode.centerRightChild;
                        child4 = thisNode.rightChild;

                        // thisNode.leftChild = null;
                        thisNode.centerLeftChild = null;
                        thisNode.centerRightChild = null;
                        thisNode.rightChild = null;

                    }

                    //update value in current node
                    int tmp = thisNode.value3;
                    thisNode.value2 = 0;
                    thisNode.value3 = 0;
                    thisNode.values = 1;

                    //modify structure
                    thisNode.parent.centerChild = new TwoFourTreeItem(tmp);
                    thisNode.parent.centerChild.parent = thisNode.parent;

                    //reconnect with children if necessary
                    if(!thisNode.isLeaf){
                        //reconnect at current node
                        thisNode.rightChild = child2;
                        thisNode.rightChild.parent = thisNode;
                        
                        //reconnect at center node
                        thisNode.parent.centerChild.leftChild = child3;
                        thisNode.parent.centerChild.rightChild = child4;

                        thisNode.parent.centerChild.leftChild.parent = thisNode.parent.centerChild;
                        thisNode.parent.centerChild.rightChild.parent = thisNode.parent.centerChild;
                        

                        //update leaf status
                        thisNode.isLeaf = false;
                        thisNode.parent.centerChild.isLeaf = false;
                    }

                }
                
                //case 2: current node is the right child
                else{
                    
                    // save middle value to its thisNode.parent
                    thisNode.parent.value2 = thisNode.value2;

                    //check if current node has children
                    if(!thisNode.isLeaf){
                        // System.out.println("THis node is not leaf");
                        child1 = thisNode.leftChild;
                        child2 = thisNode.centerLeftChild;
                        // System.out.println("Valur of left child of center node is " + child2.value1);
                        child3 = thisNode.centerRightChild;
                        child4 = thisNode.rightChild;

                        //set children to null
                        thisNode.leftChild = null;
                        thisNode.centerLeftChild = null;
                        thisNode.centerRightChild = null;
                    }

                    // update values 
                    int tmp = thisNode.value1;
                    thisNode.value1 = thisNode.value3;
                    thisNode.value2 = 0;
                    thisNode.value3 = 0;
                    thisNode.values = 1;
    
                    // modify the structre
                    thisNode.parent.centerChild = new TwoFourTreeItem(tmp);
                    thisNode.parent.centerChild.parent = thisNode.parent;

                    if(!thisNode.isLeaf){
                        //reconnect with children at current node
                        thisNode.leftChild = child3;
                        thisNode.leftChild.parent = thisNode;

                        //reconncet at center node
                        thisNode.parent.centerChild.leftChild = child1;
                        thisNode.parent.centerChild.leftChild.parent = thisNode.parent.centerChild;

                        thisNode.parent.centerChild.rightChild = child2;
                        thisNode.parent.centerChild.rightChild.parent = thisNode.parent.centerChild;


                        //update left status
                        thisNode.isLeaf = false;
                        thisNode.parent.centerChild.isLeaf = false;
                    }
                    
                }


                thisNode.parent.values++;

            } 
            
            //if thisNode.parent is three node
            else if (thisNode.parent.isThreeNode()) {

                int middleValue = thisNode.value2;

                //case 1: current node is the left child
                if(middleValue < thisNode.parent.value1){
                    //update values in thisNode.parent node
                    thisNode.parent.value3 = thisNode.parent.value2;
                    thisNode.parent.value2 = thisNode.parent.value1;
                    thisNode.parent.value1 = middleValue;

                    //check if current node has children
                    if(!thisNode.isLeaf){
                        child1 = thisNode.leftChild;
                        child2 = thisNode.centerLeftChild;
                        child3 = thisNode.centerRightChild;
                        child4 = thisNode.rightChild;

                        // thisNode.leftChild = null;
                        thisNode.centerLeftChild = null;
                        thisNode.centerRightChild = null;
                        thisNode.rightChild = null;
                    }

                    
                    //modify structure and reset values in current node
                    thisNode.parent.centerLeftChild = new TwoFourTreeItem(thisNode.value3);
                    thisNode.parent.centerLeftChild.parent = thisNode.parent;
                    thisNode.parent.centerRightChild = thisNode.parent.centerChild;
                    thisNode.parent.centerChild = null;

                    //reconnct to the children if necessary
                    if(!thisNode.isLeaf){
                        thisNode.rightChild = child2;
                        thisNode.rightChild.parent = thisNode;

                        thisNode.parent.centerLeftChild.leftChild = child3;
                        thisNode.parent.centerLeftChild.leftChild.parent = thisNode.parent.centerLeftChild;

                        thisNode.parent.centerLeftChild.rightChild = child4;
                        thisNode.parent.centerLeftChild.rightChild.parent = thisNode.parent.centerLeftChild;
                        

                        //update left status
                        thisNode.isLeaf = false;
                        thisNode.parent.centerLeftChild.isLeaf = false;
                        thisNode.parent.centerRightChild.isLeaf = false;

                    }
                    
                    //update value in this node
                    thisNode.value2 = 0;
                    thisNode.value3 = 0;
                    thisNode.values = 1;
                }

                //case 2: current node is the center child
                else if( thisNode.parent.value1 < middleValue && middleValue < thisNode.parent.value2){
                    // System.out.println("split at center child");
                    //update values in thisNode node
                    thisNode.parent.value3 = thisNode.parent.value2;
                    thisNode.parent.value2 = middleValue;
                    
                    //check if current node has children
                    if(!thisNode.isLeaf){
                     child1 = thisNode.leftChild;
                     child2 = thisNode.centerLeftChild;
                     child3 = thisNode.centerRightChild;
                     child4 = thisNode.rightChild;

                    }


                    //modify structure,update parent status
                    thisNode.parent.centerLeftChild = new TwoFourTreeItem(thisNode.value1);
                    thisNode.parent.centerRightChild = new TwoFourTreeItem(thisNode.value3);
                    thisNode.parent.centerLeftChild.parent = thisNode.parent;
                    thisNode.parent.centerRightChild.parent = thisNode.parent;

                    //reconnect children if necessary
                    if(!thisNode.isLeaf){
                        thisNode.parent.centerLeftChild.leftChild = child1;                       
                        thisNode.parent.centerLeftChild.leftChild.parent = thisNode.parent.centerLeftChild;
                        
                        thisNode.parent.centerLeftChild.rightChild = child2;                        
                        thisNode.parent.centerLeftChild.rightChild.parent = thisNode.parent.centerLeftChild;

    
                        thisNode.parent.centerRightChild.leftChild = child3;
                        thisNode.parent.centerRightChild.leftChild.parent = thisNode.parent.centerRightChild;
                    
                        thisNode.parent.centerRightChild.rightChild = child4;
                        thisNode.parent.centerRightChild.rightChild.parent = thisNode.parent.centerRightChild;

                        //update the leaf status
                        thisNode.parent.centerLeftChild.isLeaf = false;
                        thisNode.parent.centerRightChild.isLeaf = false;
                        
                    }
                    thisNode.parent.centerChild = null;


                }
                
                //case 3: current node is the right child
                else{
                    //update values in thisNode.parent node
                    thisNode.parent.value3 = middleValue;

                    //check if current node has children
                    if(!thisNode.isLeaf){
                        child1 = thisNode.leftChild;
                        child2 = thisNode.centerLeftChild;
                        child3 = thisNode.centerRightChild;
                        child4 = thisNode.rightChild;

                        thisNode.centerLeftChild = null;
                        thisNode.centerRightChild = null;
                    }

                    //modify structure and reset values in current node
                    thisNode.parent.centerLeftChild = thisNode.parent.centerChild;
                    thisNode.parent.centerLeftChild.parent = thisNode.parent; 

                    thisNode.parent.centerRightChild = new TwoFourTreeItem(thisNode.value1);
                    thisNode.parent.centerRightChild.parent = thisNode.parent;
                    thisNode.parent.centerChild = null;
                    
                    //reconnect to children if necessary
                    if(!thisNode.isLeaf){
                        thisNode.leftChild = child3;
                        thisNode.leftChild.parent = thisNode;

                        thisNode.parent.centerRightChild.leftChild = child1;
                        thisNode.parent.centerRightChild.leftChild.parent = thisNode.parent.centerRightChild;

                        thisNode.parent.centerRightChild.rightChild = child2;
                        thisNode.parent.centerRightChild.rightChild.parent = thisNode.parent.centerRightChild;

                        //update leaf status
                        thisNode.isLeaf = false;
                        thisNode.parent.centerRightChild.isLeaf = false;

                    }
                    //update values in this node
                    thisNode.value1 = thisNode.value3;
                    thisNode.value2 = 0;
                    thisNode.value3 = 0;
                    thisNode.values = 1;

                }
                    
                thisNode.parent.values++;

            } 
            
            //if thisNode.parent is four node
            else {
                //not checked yet

                // thisNode.parent is four nodes 
                // create a new root node
                TwoFourTreeItem newRoot = new TwoFourTreeItem(thisNode.value2);
                newRoot.leftChild = new TwoFourTreeItem(thisNode.parent.value1);
                newRoot.rightChild = new TwoFourTreeItem(thisNode.parent.value3);
                
                newRoot.parent = thisNode.parent.parent;
                newRoot.leftChild.parent = newRoot;
                newRoot.rightChild.parent = newRoot;

                // modify the structure
                newRoot.leftChild.leftChild = thisNode.parent.leftChild;
                newRoot.leftChild.rightChild = thisNode.parent.centerLeftChild;

                newRoot.rightChild.leftChild = thisNode.parent.centerRightChild;
                newRoot.rightChild.rightChild = thisNode.parent.rightChild;

                // update the parent status
                newRoot.leftChild.leftChild.parent = newRoot.leftChild;
                newRoot.leftChild.rightChild.parent = newRoot.leftChild;

                newRoot.rightChild.leftChild.parent = newRoot.rightChild;
                newRoot.rightChild.rightChild.parent = newRoot.rightChild;

                //update parent of this node
                thisNode.parent = newRoot;

                
            }
            
            //check if this node has children
            if(thisNode.parent.hasChildren())
                thisNode.parent.isLeaf = false;

            return thisNode.parent;
        }
    }


    public boolean hasValue(int value) {
        if(root == null){
            return false;
        }
        return root.checkValue(value);

    }


    public TwoFourTreeItem getNextChild(TwoFourTreeItem root, int value){

        if(root.isTwoNode())
        {        
            // System.out.println("getNextChild at root: " + root.value1);
            //has value 1
            if(value < root.value1)
            {
                // System.out.println("go to the left child" + root.leftChild.value1);
                return root.leftChild;
            }
            else{

                // System.out.println("go to right child: " + root.rightChild.value1);
                return root.rightChild;
            }
        }
        else if(root.isThreeNode())
        { 
            // System.out.println("Get next child when parent is three node" + root.value1);
            //has value 1 and value 2
            if(value < root.value1){
                return root.leftChild;          //smaller than value 1
            }        
            else if(value < root.value2){
                return root.centerChild;        //between value 1 and value 2
            }      
            else{
                return root.rightChild;
            }
        
        }
        else
        { 
            // System.out.println("get next child when root is four node" + root.value1);
            TwoFourTreeItem saveRoot = null;
            //has value 1 and value 2 and value 3
            if(value < root.value1){
                saveRoot = root.leftChild;
            }
            else if(value > root.value3){
                saveRoot = root.rightChild;
            }
            else if(value < root.value2){
                saveRoot = root.centerLeftChild;
            }else if(value > root.value2){
                saveRoot = root.centerRightChild;
            }
            return saveRoot;
        }
    }

    
    //fuse and the last part
    public boolean deleteValue(int value) {
            if(root == null)
                return false;

            if(!(hasValue(value)))
                return false; 

            // we know the tree exist and the value is in the tree 
            // if the root is a 2-node and both its children are 2-nodes, fuse it
            if(root.isTwoNode() && root.leftChild.isTwoNode() && root.rightChild.isTwoNode()){

                // merge values
                root.value2 = root.value1;
                root.value1 = root.leftChild.value1;
                root.value3 = root.rightChild.value1;

                TwoFourTreeItem tempL = root.leftChild;
                TwoFourTreeItem tempR = root.rightChild;

                // merge pointers
                root.leftChild = tempL.leftChild;
                root.centerLeftChild = tempL.rightChild;
                root.centerRightChild = tempR.leftChild;
                root.rightChild = tempR.rightChild;

                root.leftChild.parent = root;
                root.centerLeftChild.parent = root;
                root.centerRightChild.parent = root;
                root.rightChild.parent = root;

                root.values = 3;
            }

            //now cursively going downwards until you find T
            TwoFourTreeItem curNode = root;
            while (true){
                //if current node is two nodes, then fuse or rotate
                if(curNode.parent != null){
                    if(curNode.isTwoNode()){
                        //parent is three nodes
                        if(curNode.parent.isThreeNode()){

                            if(curNode == curNode.parent.leftChild){
                                if(!(curNode.parent.centerChild.isTwoNode())){
                                    //siblings are not two node, do rotation
                                    curNode = rotate(curNode);
                                }
                                else{
                                    curNode = fuse(curNode);
                                }
                            }
                            else if(curNode == curNode.parent.centerChild){
                                if(!(curNode.parent.rightChild.isTwoNode()) || !(curNode.parent.leftChild.isTwoNode()) ){
                                    //siblings are not two node, do rotation
                                    curNode = rotate(curNode);
                                }
                                else{
                                    curNode = fuse(curNode);
                                }

                            }
                            else if(curNode == curNode.parent.rightChild){
                                if(!(curNode.parent.centerChild.isTwoNode())){
                                    //siblings are not two node, do rotation
                                    curNode = rotate(curNode);
                                }
                                else{
                                    curNode = fuse(curNode);
                                }
                            }
                        }

                        //parent is four nodes
                        else if(curNode.parent.isFourNode()){
                            //curNode is left child
                            if(curNode == curNode.parent.leftChild){

                                if(!(curNode.parent.centerLeftChild.isTwoNode())){
                                    //siblings are not two node, do rotation
                                    curNode = rotate(curNode);
                                }
                                else{
                                    curNode = fuse(curNode);
                                }

                            }    

                            //curNode is centerLeftChild
                            else if(curNode == curNode.parent.centerLeftChild) {

                                if(!(curNode.parent.centerRightChild.isTwoNode()) || !(curNode.parent.leftChild.isTwoNode())){
                                    //siblings are not two node, do rotation
                                    curNode = rotate(curNode);
                                }else{
                                    curNode = fuse(curNode);
                                }
                            }

                            //curNode is center right child
                            else if(curNode == curNode.parent.centerRightChild){
                                if(!(curNode.parent.rightChild.isTwoNode()) || !(curNode.parent.centerLeftChild.isTwoNode())){
                                    //siblings are not two node, do rotation
                                    curNode = rotate(curNode);
                                }else{
                                    curNode = fuse(curNode);
                                }
                            }

                            //curNode is right child
                            else if(curNode == curNode.parent.rightChild){

                                if(!(curNode.parent.centerRightChild.isTwoNode())){
                                    //siblings are not two node, do rotation
                                    curNode = rotate(curNode);
                                }else{
                                    curNode = fuse(curNode);
                                }
                            }
                        }
                        
                    }
                }
                //found T break out of the loop
                if(curNode.checkValueWithinTheNode(value)){
                    break;
                }
                
                //keep going down until find T
                curNode = getNextChild(curNode, value);
            
                
            }

            //if we reach here, it gurantees that T not in two nodes 
            //T is in the leaf
            if(curNode.isLeaf){
                //direct delete
                curNode.directDeleteAtLeafNode(value);
            }
            //T is not in the leaf
            else{
                
                TwoFourTreeItem RP = null;
                TwoFourTreeItem sibling = null;
                int prv2;

                if(curNode.isThreeNode()){
                    if(value == curNode.value1){
                        RP = curNode.centerChild;
                    }
                    else if(value == curNode.value2){
                        RP = curNode.rightChild;
                    }

                    while(true){
                        if(RP.isTwoNode()){
                            if(RP.value1 == 41) System.out.println("Reach here");
                            //problem is when it fuses, it become four nodes and there is no case when it is four node
                            if(RP == curNode.centerChild){
                                //fuse or rotate with left sibling
                                if(!(RP.parent.leftChild.isTwoNode())){
                                     // rotate clockwise
                                    prv2 = RP.parent.value1;
                                    RP.value2 = RP.value1;
                                    RP.value1 = prv2;
                                    if(RP.parent.leftChild.isThreeNode())
                                        RP.parent.value1 = RP.parent.leftChild.value2;
                                    if(RP.parent.leftChild.isFourNode())
                                        RP.parent.value1 = RP.parent.leftChild.value3;
                                    RP.centerChild = RP.leftChild;
                                    RP.leftChild = RP.parent.leftChild.rightChild;
                                    if(RP.leftChild != null)
                                        RP.leftChild.parent = RP;
                                    RP.values++;

                                    // fix L values
                                    if(RP.parent.leftChild.isThreeNode())
                                        RP.parent.leftChild.value2 = 0;
                                    if(RP.parent.leftChild.isFourNode())
                                        RP.parent.leftChild.value3 = 0;

                                    // fix L pointers
                                    if(RP.parent.leftChild.isThreeNode()){
                                        RP.parent.leftChild.rightChild = RP.parent.leftChild.centerChild;
                                        RP.parent.leftChild.centerChild = null;
                                    }
                                    if(RP.parent.leftChild.isFourNode()){
                                        RP.parent.leftChild.rightChild = RP.parent.leftChild.centerRightChild;
                                        RP.parent.leftChild.centerChild = RP.parent.leftChild.centerLeftChild;
                                        RP.parent.leftChild.centerLeftChild = null;
                                        RP.parent.leftChild.centerRightChild = null;
                                    }
                                    RP.parent.leftChild.values--; 
                                }else{
                                    RP = fuse(RP.parent.leftChild);
                                    if(RP == RP.parent.leftChild) System.out.println("now it become left child");
                                    System.out.println("at value center left child : " + RP.centerLeftChild.value1 + " at value center right child: " + RP.centerRightChild.value1);
                                }
                            }else if (RP == curNode.rightChild){
                                //fuse or rotate with center child
                                if(!RP.parent.centerChild.isTwoNode()){
                                    RP = rotate(RP);
                                }else{
                                    RP = fuse(RP);
                                }
                            }else{
                                //get the sibling then check if sibling is two node
                                sibling = getSibling(RP);
                                if(sibling.isTwoNode()){
                                    RP = fuse(RP);
                                }else{
                                    RP = rotate(RP);
                                }

                                
                            }

                        }

                        if(RP.isLeaf){
                            if(RP.checkValueWithinTheNode(value)){

                                if(RP.value1 == value){
                                    RP.value1 = RP.value2;
                                    RP.value2 = RP.value3;
                                }
                                else if(RP.value2 == value){
                                    RP.value2 = RP.value3;
                                    RP.value3 = 0;
                                }
                                else if(RP.value3 == value){
                                    RP.value3 = 0;
                                }
                                RP.values--;
                            }
                            else{
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

                        //RP is not leaf also not two nodes
                        //keep going down

                        RP = getImmediateRightChild(RP, value);
                        if(RP.value1 == 41) System.out.println("reach here");
                    }


                }
                else if(curNode.isFourNode()){
                    if(value == curNode.value1){
                        RP = curNode.centerLeftChild;
                    }
                    if(value == curNode.value2){
                        RP = curNode.centerRightChild;
                    }
                    if(value == curNode.value3){
                        RP = curNode.rightChild;
                    }

                    while(true){
                        if(RP.isTwoNode()){
                            if(RP == curNode.centerLeftChild){
                                //fuse or rotate with left sibling, rotate clockwise
                                if(!(RP.parent.leftChild.isTwoNode())){
                                    prv2 = RP.parent.value1;
                                    RP.value2 = RP.value1;
                                    RP.value1 = prv2;
                                    if(RP.parent.leftChild.isThreeNode())
                                        RP.parent.value1 = RP.parent.leftChild.value2;
                                    if(RP.parent.leftChild.isFourNode())
                                        RP.parent.value1 = RP.parent.leftChild.value3;
                                    RP.centerChild = RP.leftChild;
                                    RP.leftChild = RP.parent.leftChild.rightChild;
                                    if(RP.leftChild != null)
                                        RP.leftChild.parent = RP;
                                    RP.values++;

                                    // fix L values
                                    if(RP.parent.leftChild.isThreeNode())
                                        RP.parent.leftChild.value2 = 0;
                                    if(RP.parent.leftChild.isFourNode())
                                        RP.parent.leftChild.value3 = 0;

                                    // fix L pointers
                                    if(RP.parent.leftChild.isThreeNode()){
                                        RP.parent.leftChild.rightChild = RP.parent.leftChild.centerChild;
                                        RP.parent.leftChild.centerChild = null;
                                    }
                                    if(RP.parent.leftChild.isFourNode()){
                                        RP.parent.leftChild.rightChild = RP.parent.leftChild.centerRightChild;
                                        RP.parent.leftChild.centerChild = RP.parent.leftChild.centerLeftChild;
                                        RP.parent.leftChild.centerLeftChild = null;
                                        RP.parent.leftChild.centerRightChild = null;
                                    }
                                    RP.parent.leftChild.values--;
                                    
                                }else{
                                    RP = fuse(RP.parent.leftChild);
                                }
                            }else if (RP == curNode.centerRightChild){
                                // System.out.println("RP is center right child");
                                //fuse or rotate with center left child
                                if(!(RP.parent.centerLeftChild.isTwoNode())){
                                    //rotate with left only
                                    // RP = fuse(RP);
                                    prv2 = RP.parent.value2;
                                    RP.value2 = RP.value1;
                                    RP.value1 = prv2;
                                    RP.values++;

                                    if(RP.parent.centerLeftChild.isThreeNode())
                                        RP.parent.value2 = RP.parent.centerLeftChild.value2;
                                    if(RP.parent.centerLeftChild.isFourNode())
                                        RP.parent.value2 = RP.parent.centerLeftChild.value3;
                                    RP.centerChild = RP.leftChild;
                                    RP.leftChild = RP.parent.centerLeftChild.rightChild;
                                    RP.leftChild.parent = RP;

                                    // fix L values
                                    if(RP.parent.centerLeftChild.isThreeNode())
                                        RP.parent.centerLeftChild.value2 = 0;
                                    if(RP.parent.centerLeftChild.isFourNode())
                                        RP.parent.centerLeftChild.value3 = 0;

                                    // fix L pointers
                                    if(RP.parent.centerLeftChild.isThreeNode()){
                                        RP.parent.centerLeftChild.rightChild = RP.parent.centerLeftChild.centerChild;
                                        RP.parent.centerLeftChild.centerChild = null;
                                    }
                                    if(RP.parent.centerLeftChild.isFourNode()){
                                        RP.parent.centerLeftChild.rightChild = RP.parent.centerLeftChild.centerRightChild;
                                        RP.parent.centerLeftChild.centerChild = RP.parent.centerLeftChild.centerLeftChild;
                                        RP.parent.centerLeftChild.centerLeftChild = null;
                                        RP.parent.centerLeftChild.centerRightChild = null;
                                    }
                                    RP.parent.centerLeftChild.values--;
                                }else{

                                    RP = fuse(RP);
                                }
                            }
                            else if(RP == curNode.rightChild){
                                // System.out.println("Rp is right child");
                                if(!(RP.parent.centerRightChild.isTwoNode())){
                                    //rotate
                                    RP = rotate(RP);
                                }else{
                                    //fuse
                                    RP = fuse(RP);
                                }
                            }
                            
                            else{
                                //get the sibling then check if sibling is two node
                                // System.out.println("RP is not above");
                                sibling = getSibling(RP);
                                if(sibling.isTwoNode()){
                                    RP = fuse(RP);
                                }else{
                                    RP = rotate(RP);
                                }
                                
                            }
                            
                        }
                        
                        
                        // if(RP == null) System.out.println("RP is null");
                        // else System.out.println("RP is not null");
                        
                        if(RP.isLeaf){
                            if(RP.checkValueWithinTheNode(value)){
                                
                                if(RP.value1 == value){
                                    RP.value1 = RP.value2;
                                    RP.value2 = RP.value3;
                                }
                                else if(RP.value2 == value){
                                    RP.value2 = RP.value3;
                                    RP.value3 = 0;
                                }
                                else if(RP.value3 == value){
                                    RP.value3 = 0;
                                }
                                RP.values--;
                            }

                        else{
                                //swap then delete
                                if(curNode.value1 == value)
                                    curNode.value1 = RP.value1;
                                
                                if(curNode.value2 == value)
                                    curNode.value2 = RP.value1;
                                
                                if(curNode.value3 == value)
                                    curNode.value3 = RP.value1;

                                RP.value1 = RP.value2;
                                RP.value2 = RP.value3;
                                RP.value3 = 0;
                                RP.values--;
                        }

                        break;

                        }
                    
                        //RP is not leaf also not two nodes
                        //keep going down
                        // System.out.println("reach here");
                        RP = getImmediateRightChild(RP, value);
                    
                    }

                }

            }
        //deletion complete
        return true;
    }

   
    public TwoFourTreeItem getImmediateRightChild(TwoFourTreeItem RP,int value){
        
        if(RP.isThreeNode()){
            if(value == RP.value1){
                return RP.centerChild;
            }
            else if(value == RP.value2){
                return RP.rightChild;
            }
            else{
                return RP.leftChild;
            }
        }
        else if(RP.isFourNode()){
            if(value == RP.value1){
                return RP.centerLeftChild;
            }
            else if(value == RP.value2){
                return RP.centerRightChild;
            }
            else if(value == RP.value3){
                return RP.rightChild;
            }
            else{
                return RP.leftChild;
            }
        }

        return null;
    }

    //take in the value of current node and the node
    public TwoFourTreeItem getSibling(TwoFourTreeItem curNode){
        TwoFourTreeItem sibling = null;
        TwoFourTreeItem parent = curNode.parent;

        if(curNode.parent.isTwoNode()){
            if(curNode == parent.leftChild){
                sibling = parent.rightChild;
            }

            if(curNode == parent.rightChild){
                sibling = parent.leftChild;
            }

        }

        else if(curNode.parent.isThreeNode()){
            //curNode is left child
            if(curNode == parent.leftChild){
                sibling = parent.centerChild;
            }
            //curNode is right child
            if(curNode == parent.rightChild){
                sibling = parent.centerChild;   
            }

            //curNode is center child, I do not think it will reach this case, for now return the right child
            if(curNode == parent.centerChild){
                sibling = parent.rightChild;
            }

        }

        else if(curNode.parent.isFourNode()){
            if(curNode == parent.leftChild){
                sibling = parent.centerLeftChild;
            }

            //for now choose sibling on the right side
            if(curNode == parent.centerLeftChild){
                sibling = parent.centerRightChild;
            }
            //for now choose sibling on the right side
            if(curNode == parent.centerRightChild){
                sibling = parent.centerLeftChild;
            }

            if(curNode == parent.rightChild){
                sibling = parent.centerRightChild;
            }

        }

        return sibling;
    }

    public TwoFourTreeItem fuse(TwoFourTreeItem curNode){

        // if parent is 3 node
        if(curNode.parent.isThreeNode()){

            // leftChild fuse with center child
            if(curNode == curNode.parent.leftChild){
                // merge values
                curNode.value2 = curNode.parent.value1;
                curNode.value3 = curNode.parent.centerChild.value1;

                curNode.parent.value1 = curNode.parent.value2;
                curNode.parent.value2 = 0;
                curNode.parent.values--;

                // merge pointers
                curNode.centerLeftChild = curNode.rightChild;
                curNode.centerRightChild = curNode.parent.centerChild.leftChild;
                curNode.rightChild = curNode.parent.centerChild.rightChild;

                if (curNode.centerRightChild != null)
                    curNode.centerRightChild.parent = curNode;
                if (curNode.rightChild != null)
                    curNode.rightChild.parent = curNode;

                curNode.parent.centerChild = null;
            }

            // centerChild merge with the right
            else if(curNode == curNode.parent.centerChild){
                // merge values
                curNode.value2 = curNode.parent.value2;
                curNode.value3 = curNode.parent.rightChild.value1;

                curNode.parent.value2 = 0;
                curNode.parent.values--;

                // merge pointers
                curNode.centerLeftChild = curNode.rightChild;
                curNode.centerRightChild = curNode.parent.rightChild.leftChild;
                curNode.rightChild = curNode.parent.rightChild.rightChild;

                if(curNode.centerRightChild != null)
                    curNode.centerRightChild.parent = curNode;
                if(curNode.rightChild != null)
                    curNode.rightChild.parent = curNode;

                curNode.parent.rightChild = curNode;
                curNode.parent.centerChild = null; 
            }

            // rightChild, fuse with center child
            else if(curNode == curNode.parent.rightChild){
                // merge values
                curNode.value3 = curNode.value1;
                curNode.value2 = curNode.parent.value2;
                curNode.value1 = curNode.parent.centerChild.value1;

                curNode.parent.value2 = 0;
                curNode.parent.values--;

                // merge pointers
                curNode.centerRightChild = curNode.leftChild;
                curNode.centerLeftChild = curNode.parent.centerChild.rightChild;
                curNode.leftChild = curNode.parent.centerChild.leftChild;

                if(curNode.centerLeftChild != null)
                    curNode.centerLeftChild.parent = curNode;
                if(curNode.leftChild != null)
                    curNode.leftChild.parent = curNode;

                curNode.parent.centerChild = null;

            }

            curNode.values = 3;
        }

        // if parent is 4 node
        if(curNode.parent.isFourNode()){

            // leftChild, fuse with center left child
            if(curNode == curNode.parent.leftChild){
                // merge values
                curNode.value2 = curNode.parent.value1;
                curNode.value3 = curNode.parent.centerLeftChild.value1;

                curNode.parent.value1 = curNode.parent.value2;
                curNode.parent.value2 = curNode.parent.value3;
                curNode.parent.value3 = 0;
                curNode.parent.values--;

                // merge pointers
                curNode.centerLeftChild = curNode.rightChild;
                curNode.centerRightChild = curNode.parent.centerLeftChild.leftChild;
                curNode.rightChild = curNode.parent.centerLeftChild.rightChild;

                if(curNode.centerRightChild != null)
                    curNode.centerRightChild.parent = curNode;
                if(curNode.rightChild != null)
                    curNode.rightChild.parent = curNode;

                curNode.parent.centerChild = curNode.parent.centerRightChild;
                
                curNode.parent.centerLeftChild = null;
                curNode.parent.centerRightChild = null;

            }

            // centerLeftChild or centerRightChild
            else if(curNode == curNode.parent.centerLeftChild || curNode == curNode.parent.centerRightChild){

                curNode.value2 = curNode.parent.value2;
                curNode.parent.value2 = curNode.parent.value3;
                curNode.parent.value3 = 0;
                curNode.parent.values--;

                if(curNode == curNode.parent.centerLeftChild){
                    
                    curNode.value3 = curNode.parent.centerRightChild.value1;

                    curNode.centerLeftChild = curNode.rightChild;
                    curNode.centerRightChild = curNode.parent.centerRightChild.leftChild;
                    curNode.rightChild = curNode.parent.centerRightChild.rightChild;

                    if(curNode.centerRightChild != null)
                        curNode.centerRightChild.parent = curNode;
                    if(curNode.rightChild != null)
                        curNode.rightChild.parent = curNode;

                }
                if(curNode == curNode.parent.centerRightChild){
                    
                    curNode.value3 = curNode.value1;
                    curNode.value1 = curNode.parent.centerLeftChild.value1;

                    curNode.centerRightChild = curNode.leftChild;
                    curNode.centerLeftChild = curNode.parent.centerLeftChild.rightChild;
                    curNode.leftChild = curNode.parent.centerLeftChild.leftChild;

                    if(curNode.centerLeftChild != null)
                        curNode.centerLeftChild.parent = curNode;
                    if(curNode.leftChild != null)
                        curNode.leftChild.parent = curNode;
                    
                }

                //merge
                curNode.parent.centerChild = curNode;

                curNode.parent.centerLeftChild = null;
                curNode.parent.centerRightChild = null;
            }

            // rightChild
            else if( curNode == curNode.parent.rightChild){

                // merge values
                curNode.value3 = curNode.value1;
                curNode.value2 = curNode.parent.value3;
                curNode.value1 = curNode.parent.centerRightChild.value1;

                curNode.parent.value3 = 0;
                curNode.parent.values--;

                // merge pointers
                curNode.centerRightChild = curNode.leftChild;
                curNode.centerLeftChild = curNode.parent.centerRightChild.rightChild;
                curNode.leftChild = curNode.parent.centerRightChild.leftChild;

                if(curNode.centerLeftChild != null)
                    curNode.centerLeftChild.parent = curNode;
                if(curNode.leftChild != null)
                    curNode.leftChild.parent = curNode;

                curNode.parent.centerChild = curNode.parent.centerLeftChild;

                curNode.parent.centerRightChild = null;
                curNode.parent.centerLeftChild = null;
            }

            curNode.values = 3;

        }

        return curNode;
    }

    //rotate and return the current node that thas more than one key.
    public TwoFourTreeItem  rotate(TwoFourTreeItem curNode){

            int prv;

            // if parent is 3 node
            if(curNode.parent.isThreeNode()){

                // leftChild, anticlockwise
                if(curNode == curNode.parent.leftChild && !(curNode.parent.centerChild.isTwoNode())){

                    // rotate anticlockwise
                    prv = curNode.parent.value1;
                    curNode.value2 = prv;
                    curNode.parent.value1 = curNode.parent.centerChild.value1;
                    curNode.centerChild = curNode.rightChild;
                    curNode.rightChild = curNode.parent.centerChild.leftChild;
                    if(curNode.rightChild != null)
                        curNode.rightChild.parent = curNode;
                    curNode.values++;

                    // fix R values
                    curNode.parent.centerChild.value1 = curNode.parent.centerChild.value2;
                    curNode.parent.centerChild.value2 = curNode.parent.centerChild.value3;
                    curNode.parent.centerChild.value3 = 0;

                    // fix R pointers
                    if(curNode.parent.centerChild.isThreeNode()){
                        curNode.parent.centerChild.leftChild = curNode.parent.centerChild.centerChild;
                        curNode.parent.centerChild.centerChild = null;
                    }
                    if(curNode.parent.centerChild.isFourNode()){
                        curNode.parent.centerChild.leftChild = curNode.parent.centerChild.centerLeftChild;
                        curNode.parent.centerChild.centerChild = curNode.parent.centerChild.centerRightChild;
                        curNode.parent.centerChild.centerLeftChild = null;
                        curNode.parent.centerChild.centerRightChild = null;
                    }
                    curNode.parent.centerChild.values--;
                    return curNode;

                }

                // centerChild, anticlockwise
                else if(curNode == curNode.parent.centerChild && !(curNode.parent.rightChild.isTwoNode())){

                    // rotate anticlockwise
                    prv = curNode.parent.value2;
                    curNode.value2 = prv;
                    curNode.parent.value2 = curNode.parent.rightChild.value1;
                    curNode.centerChild = curNode.rightChild;
                    curNode.rightChild = curNode.parent.rightChild.leftChild;
                    if(curNode.rightChild != null)
                        curNode.rightChild.parent = curNode;
                    curNode.values++;

                    // fix R values
                    curNode.parent.rightChild.value1 = curNode.parent.rightChild.value2;
                    curNode.parent.rightChild.value2 = curNode.parent.rightChild.value3;
                    curNode.parent.rightChild.value3 = 0;

                    // fix R pointers
                    if(curNode.parent.rightChild.isThreeNode()){
                        curNode.parent.rightChild.leftChild = curNode.parent.rightChild.centerChild;
                        curNode.parent.rightChild.centerChild = null;
                    }
                    if(curNode.parent.rightChild.isFourNode()){
                        curNode.parent.rightChild.leftChild = curNode.parent.rightChild.centerLeftChild;
                        curNode.parent.rightChild.centerChild = curNode.parent.rightChild.centerRightChild;
                        curNode.parent.rightChild.centerLeftChild = null;
                        curNode.parent.rightChild.centerRightChild = null;
                    }
                    curNode.parent.rightChild.values--;
                    return curNode;

                }

                // centerChild, clockwise
                if(curNode == curNode.parent.centerChild && !(curNode.parent.leftChild.isTwoNode())){

                    // rotate clockwise
                    prv = curNode.parent.value1;
                    curNode.value2 = curNode.value1;
                    curNode.value1 = prv;
                    if(curNode.parent.leftChild.isThreeNode())
                        curNode.parent.value1 = curNode.parent.leftChild.value2;
                    if(curNode.parent.leftChild.isFourNode())
                        curNode.parent.value1 = curNode.parent.leftChild.value3;
                    curNode.centerChild = curNode.leftChild;
                    curNode.leftChild = curNode.parent.leftChild.rightChild;
                    if(curNode.leftChild != null)
                        curNode.leftChild.parent = curNode;
                    curNode.values++;

                    // fix L values
                    if(curNode.parent.leftChild.isThreeNode())
                        curNode.parent.leftChild.value2 = 0;
                    if(curNode.parent.leftChild.isFourNode())
                        curNode.parent.leftChild.value3 = 0;

                    // fix L pointers
                    if(curNode.parent.leftChild.isThreeNode()){
                        curNode.parent.leftChild.rightChild = curNode.parent.leftChild.centerChild;
                        curNode.parent.leftChild.centerChild = null;
                    }
                    if(curNode.parent.leftChild.isFourNode()){
                        curNode.parent.leftChild.rightChild = curNode.parent.leftChild.centerRightChild;
                        curNode.parent.leftChild.centerChild = curNode.parent.leftChild.centerLeftChild;
                        curNode.parent.leftChild.centerLeftChild = null;
                        curNode.parent.leftChild.centerRightChild = null;
                    }
                    curNode.parent.leftChild.values--;
                    return curNode;

                }

                // rightChild, clockwise
                if(curNode == curNode.parent.rightChild && !(curNode.parent.centerChild.isTwoNode())){

                    // rotate clockwise
                    prv = curNode.parent.value2;
                    curNode.value2 = curNode.value1;
                    curNode.value1 = prv;
                    if(curNode.parent.centerChild.isThreeNode())
                        curNode.parent.value2 = curNode.parent.centerChild.value2;
                    if(curNode.parent.centerChild.isFourNode())
                       curNode.parent.value2 = curNode.parent.centerChild.value3;
                    curNode.centerChild = curNode.leftChild;
                    curNode.leftChild = curNode.parent.centerChild.rightChild;
                    if(curNode.leftChild != null)
                        curNode.leftChild.parent = curNode;
                    curNode.values++;

                    // fix L values
                    if(curNode.parent.centerChild.isThreeNode())
                        curNode.parent.centerChild.value2 = 0;
                    if(curNode.parent.centerChild.isFourNode())
                        curNode.parent.centerChild.value3 = 0;

                    // fix L pointers
                    if(curNode.parent.centerChild.isThreeNode()){
                        curNode.parent.centerChild.rightChild = curNode.parent.centerChild.centerChild;
                        curNode.parent.centerChild.centerChild = null;
                    }
                    if(curNode.parent.centerChild.isFourNode()){
                        curNode.parent.centerChild.rightChild = curNode.parent.centerChild.centerRightChild;
                        curNode.parent.centerChild.centerChild = curNode.parent.centerChild.centerLeftChild;
                        curNode.parent.centerChild.centerLeftChild = null;
                        curNode.parent.centerChild.centerRightChild = null;
                    }
                    curNode.parent.leftChild.values--;
                    return curNode;

                }

            }

            // if parent is 4 node
            if(curNode.parent.isFourNode()){

                // leftChild, anticlockwise
                if(curNode == curNode.parent.leftChild && !(curNode.parent.centerLeftChild.isTwoNode())){

                    // rotate anticlockwise
                    prv = curNode.parent.value1;
                    curNode.value2 = prv;
                    curNode.parent.value1 = curNode.parent.centerLeftChild.value1;
                    curNode.centerChild = curNode.rightChild;
                    curNode.rightChild = curNode.parent.centerLeftChild.leftChild;
                    curNode.rightChild.parent = curNode;
                    curNode.values++;

                    // fix R values
                    curNode.parent.centerLeftChild.value1 = curNode.parent.centerLeftChild.value2;
                    curNode.parent.centerLeftChild.value2 = curNode.parent.centerLeftChild.value3;
                    curNode.parent.centerLeftChild.value3 = 0;

                    // fix R pointers
                    if(curNode.parent.centerLeftChild.isThreeNode()){
                        curNode.parent.centerLeftChild.leftChild = curNode.parent.centerLeftChild.centerChild;
                        curNode.parent.centerLeftChild.centerChild = null;
                    }
                    if(curNode.parent.centerLeftChild.isFourNode()){
                        curNode.parent.centerLeftChild.leftChild = curNode.parent.centerLeftChild.centerLeftChild;
                        curNode.parent.centerLeftChild.centerChild = curNode.parent.centerLeftChild.centerRightChild;
                        curNode.parent.centerLeftChild.centerLeftChild = null;
                        curNode.parent.centerLeftChild.centerRightChild = null;
                    }
                    curNode.parent.centerLeftChild.values--;
                    return curNode;

                }

                // centerLeftChild, anticlockwise
                if(curNode == curNode.parent.centerLeftChild && !(curNode.parent.centerRightChild.isTwoNode())){

                    // rotate anticlockwise
                    prv = curNode.parent.value2;
                    curNode.value2 = prv;
                    curNode.parent.value2 = curNode.parent.centerRightChild.value1;
                    curNode.centerChild = curNode.rightChild;
                    curNode.rightChild = curNode.parent.centerRightChild.leftChild;
                    curNode.rightChild.parent = curNode;
                    curNode.values++;

                    // fix R values
                    curNode.parent.centerRightChild.value1 = curNode.parent.centerRightChild.value2;
                    curNode.parent.centerRightChild.value2 = curNode.parent.centerRightChild.value3;
                    curNode.parent.centerRightChild.value3 = 0;

                    // fix R pointers
                    if(curNode.parent.centerRightChild.isThreeNode()){
                        curNode.parent.centerRightChild.leftChild = curNode.parent.centerRightChild.centerChild;
                        curNode.parent.centerRightChild.centerChild = null;
                    }
                    if(curNode.parent.centerRightChild.isFourNode()){
                        curNode.parent.centerRightChild.leftChild = curNode.parent.centerRightChild.centerLeftChild;
                        curNode.parent.centerRightChild.centerChild = curNode.parent.centerRightChild.centerRightChild;
                        curNode.parent.centerRightChild.centerLeftChild = null;
                        curNode.parent.centerRightChild.centerRightChild = null;
                    }
                    curNode.parent.centerRightChild.values--;
                    return curNode;

                }

                // centerRightChild, anticlockwise
                if(curNode == curNode.parent.centerRightChild && !(curNode.parent.rightChild.isTwoNode())){

                    // rotate anticlockwise
                    prv = curNode.parent.value3;
                    curNode.value2 = prv;
                    curNode.parent.value3 = curNode.parent.rightChild.value1;
                    curNode.centerChild = curNode.rightChild;
                    curNode.rightChild = curNode.parent.rightChild.leftChild;
                    curNode.rightChild.parent = curNode;
                    curNode.values++;

                    // fix R values
                    curNode.parent.rightChild.value1 = curNode.parent.rightChild.value2;
                    curNode.parent.rightChild.value2 = curNode.parent.rightChild.value3;
                    curNode.parent.rightChild.value3 = 0;

                    // fix R pointers
                    if(curNode.parent.rightChild.isThreeNode()){
                        curNode.parent.rightChild.leftChild = curNode.parent.rightChild.centerChild;
                        curNode.parent.rightChild.centerChild = null;
                    }
                    if(curNode.parent.rightChild.isFourNode()){
                        curNode.parent.rightChild.leftChild = curNode.parent.rightChild.centerLeftChild;
                        curNode.parent.rightChild.centerChild = curNode.parent.rightChild.centerRightChild;
                        curNode.parent.rightChild.centerLeftChild = null;
                        curNode.parent.rightChild.centerRightChild = null;
                    }
                    curNode.parent.rightChild.values--;
                    return curNode;

                }

                // centerLeftChild, clockwise
                if(curNode == curNode.parent.centerLeftChild && !(curNode.parent.leftChild.isTwoNode())){

                    // rotate clockwise
                    prv = curNode.parent.value1;
                    curNode.value2 = curNode.value1;
                    curNode.value1 = prv;
                    if(curNode.parent.leftChild.isThreeNode())
                        curNode.parent.value1 = curNode.parent.leftChild.value2;
                    if(curNode.parent.leftChild.isFourNode())
                        curNode.parent.value1 = curNode.parent.leftChild.value3;
                    curNode.centerChild = curNode.leftChild;
                    curNode.leftChild = curNode.parent.leftChild.rightChild;
                    if(curNode.leftChild != null)
                        curNode.leftChild.parent = curNode;
                    curNode.values++;

                    // fix L values
                    if(curNode.parent.leftChild.isThreeNode())
                        curNode.parent.leftChild.value2 = 0;
                    if(curNode.parent.leftChild.isFourNode())
                        curNode.parent.leftChild.value3 = 0;

                    // fix L pointers
                    if(curNode.parent.leftChild.isThreeNode()){
                        curNode.parent.leftChild.rightChild = curNode.parent.leftChild.centerChild;
                        curNode.parent.leftChild.centerChild = null;
                    }
                    if(curNode.parent.leftChild.isFourNode()){
                        curNode.parent.leftChild.rightChild = curNode.parent.leftChild.centerRightChild;
                        curNode.parent.leftChild.centerChild = curNode.parent.leftChild.centerLeftChild;
                        curNode.parent.leftChild.centerLeftChild = null;
                        curNode.parent.leftChild.centerRightChild = null;
                    }
                    curNode.parent.leftChild.values--;
                    return curNode;

                }

                // centerRightChild, clockwise
                if(curNode == curNode.parent.centerRightChild && !(curNode.parent.centerLeftChild.isTwoNode())){

                    // rotate clockwise
                    prv = curNode.parent.value2;
                    curNode.value2 = curNode.value1;
                    curNode.value1 = prv;
                    if(curNode.parent.centerLeftChild.isThreeNode())
                        curNode.parent.value2 = curNode.parent.centerLeftChild.value2;
                    if(curNode.parent.centerLeftChild.isFourNode())
                        curNode.parent.value2 = curNode.parent.centerLeftChild.value3;
                    curNode.centerChild = curNode.leftChild;
                    curNode.leftChild = curNode.parent.centerLeftChild.rightChild;
                    if(curNode.leftChild != null)
                        curNode.leftChild.parent = curNode;
                    curNode.values++;

                    // fix L values
                    if(curNode.parent.centerLeftChild.isThreeNode())
                        curNode.parent.centerLeftChild.value2 = 0;
                    if(curNode.parent.centerLeftChild.isFourNode())
                        curNode.parent.centerLeftChild.value3 = 0;

                    // fix L pointers
                    if(curNode.parent.centerLeftChild.isThreeNode()){
                        curNode.parent.centerLeftChild.rightChild = curNode.parent.centerLeftChild.centerChild;
                        curNode.parent.centerLeftChild.centerChild = null;
                    }
                    if(curNode.parent.centerLeftChild.isFourNode()){
                        curNode.parent.centerLeftChild.rightChild = curNode.parent.centerLeftChild.centerRightChild;
                        curNode.parent.centerLeftChild.centerChild = curNode.parent.centerLeftChild.centerLeftChild;
                        curNode.parent.centerLeftChild.centerLeftChild = null;
                        curNode.parent.centerLeftChild.centerRightChild = null;
                    }
                    curNode.parent.centerLeftChild.values--;
                    return curNode;

                }

                // rightChild, clockwise
                if(curNode == curNode.parent.rightChild && !(curNode.parent.centerRightChild.isTwoNode())){

                    // rotate clockwise
                    prv = curNode.parent.value3;
                    curNode.value2 = curNode.value1;
                    curNode.value1 = prv;
                    if(curNode.parent.centerRightChild.isThreeNode())
                        curNode.parent.value3 = curNode.parent.centerRightChild.value2;
                    if(curNode.parent.centerRightChild.isFourNode())
                        curNode.parent.value3 = curNode.parent.centerRightChild.value3;
                    curNode.centerChild = curNode.leftChild;
                    curNode.leftChild = curNode.parent.centerRightChild.rightChild;
                    if(curNode.leftChild != null) 
                        curNode.leftChild.parent = curNode;
                    curNode.values++;

                    // fix L values
                    if(curNode.parent.centerRightChild.isThreeNode())
                        curNode.parent.centerRightChild.value2 = 0;
                    if(curNode.parent.centerRightChild.isFourNode())
                        curNode.parent.centerRightChild.value3 = 0;

                    // fix L pointers
                    if(curNode.parent.centerRightChild.isThreeNode()){
                        curNode.parent.centerRightChild.rightChild = curNode.parent.centerRightChild.centerChild;
                        curNode.parent.centerRightChild.centerChild = null;
                    }
                    if(curNode.parent.centerRightChild.isFourNode()){
                        curNode.parent.centerRightChild.rightChild = curNode.parent.centerRightChild.centerRightChild;
                        curNode.parent.centerRightChild.centerChild = curNode.parent.centerRightChild.centerLeftChild;
                        curNode.parent.centerRightChild.centerLeftChild = null;
                        curNode.parent.centerRightChild.centerRightChild = null;
                    }
                    curNode.parent.centerRightChild.values--;
                    return curNode;

                }

            }
            
            return null;
            // end of rotate
    
        }


    public TwoFourTreeItem findLeftMostOfRightChild(TwoFourTreeItem currentNode){
        if(currentNode == null)
            return null;
        
            currentNode = currentNode.rightChild;

            while(currentNode.leftChild != null){
                currentNode = currentNode.leftChild;
            }
            return currentNode;
    }

    public TwoFourTreeItem findRightMostOfLeftChild(TwoFourTreeItem currentNode){
        if(currentNode == null)
            return null;
        
            currentNode = currentNode.leftChild;

            while(currentNode.rightChild != null){
                currentNode = currentNode.rightChild;
            }
            return currentNode;
    }


    public void printInOrder() {
        if (root != null)
            root.printInOrder(0);
    }
    
    // create array of value and call for loop with addValue function
    public TwoFourTree() {
        root = null;
    }

}
