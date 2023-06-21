
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


    public boolean deleteValue(int value) {
        if(root == null){
            return false;
        }else if(!root.checkValue(value)){
            return false;
        }else{
            return true;
        }
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
