/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SortingAlgorithmSimulation;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;


/**
 *
 * @author Nasar
 */
public class FXMLDocumentController implements Initializable {
    private BackgroundSorting sort ;//= new UpdateUI();
    public String sortingVideo;
    public WebView webView;
    public WebEngine webEngine;
    
    @FXML
    Hyperlink stack, queue, recursion, tree, linkedlist;
@FXML
private StackPane videoPane;

    @FXML
    private ListView list;
     private  ObservableList<String> myList;
    
    @FXML
    private Text text1,text2,text3,text4,text5,text6,text7,text8,text9,text10; 
    private ArrayList<Text> textArray = new ArrayList(10);
     
    @FXML
    private TextField inputText1, inputText2, inputText3, inputText4, inputText5, inputText6,inputText7, inputText8, inputText9, inputText10, searchInput;
     private ArrayList<TextField> inputTextArray = new ArrayList(10);

    
   @FXML
    private ToggleGroup algorithmToggleGroup;
   
   
   @FXML
   private Slider slider;
   @FXML
   private Label speedLabel,label1,label2,label3,targetFieldLabel,targetLabel,dataInputLabel;

  
   
   @FXML
    private Button userInputButton, inputButton,resetButton,runButton, stepButton,searchButton;
   @FXML
   private Label searchLabel,searchResultLabel,NumberOfSwapLabel, currentAlgorithmLabel;
   
 
    
   private  String algorithmStr;
   
   private TranslateTransition tt1, tt2;
   
 
   
   private EventHandler sortIterationHandler;
   @FXML
   private AnchorPane bottomPane,topPane1,topPane2;
   @FXML 
   private HBox rec1,rec2,rec3,rec4,rec5,rec6,rec7,rec8,rec9,rec10; 
   private ArrayList<HBox> recArray = new ArrayList(10);
   private double speed;
   private int searchTarget;

   private Status currentStatus;
   

   
   private boolean isStepping;

   public boolean flag;
   
   private boolean callThread;
  // private int next;
   


   private enum Status {

		STOPPED, PLAYING, PAUSED;
	}
   
   private void setStatus(Status status) {

		currentStatus = status;

		switch (status) {
			case STOPPED:
				runButton.setText("Sort");
				break;
			case PLAYING:
				runButton.setText("Pause");
				break;
			case PAUSED:
				runButton.setText("Resume");
				break;
		}
	}
   
   @FXML
   private void handleHyperlink(ActionEvent event){
        
      
           sortingVideo =  "<iframe width=\"560\" height=\"315\" src=\"http://www.youtube.com/embed/SLOrrO7DlYo\" frameborder=\"0\" allowfullscreen></iframe>";
                    
         webView = new WebView();
         webEngine = webView.getEngine();
        webEngine.loadContent(sortingVideo);
         videoPane.getChildren().add(webView);  
       
   }
   @FXML
   private void handleSearchButton(ActionEvent event){

          searchInput.setDisable(true);
         searchButton.setVisible(false);
         runButton.setDisable(false);
     // handleRunAction(event);

   }
   
   @FXML
	private void handleStepAction(ActionEvent event) {
     //           flag = true;
                    if(isStepping){
                        System.out.println("inside if stepmethod");
                        isStepping = false;
                       flag = false;
                        sort.resume();
                    }
                    else{
                      //  sort.resume();
                         isStepping = true;
                       flag = true;
                   
                         System.out.println("inside else stepmethod");
                        sort.resume();
                        
                    }
                    
        }

   
   
   @FXML
    private void handleRunAction(ActionEvent event) {
      
        //   drawArrow();
       System.out.println("run button pressed");
    //   nextSortIteration();
    //   start();
    //   resetButton.setDisable(true);
     
    
  //    runButton.setDisable(true);
     //    sort.th.start();
     inputButton.setDisable(true);
      switch (currentStatus) {
			case STOPPED:
				// Change the status
				setStatus(Status.PLAYING);

				// Reset the title
				
                                System.out.println(currentStatus.toString());
		//		 Start the first iteration
			
                                flag = true;
			//	nextSortIteration();
                                stepButton.setDisable(true);
                                resetButton.setDisable(true);
                                isStepping = false;
                                sort.resume();
				break;
			case PLAYING:
				// Change the status
				setStatus(Status.PAUSED);
                                System.out.println(currentStatus.toString());
				// Cut off animation chain
				if (tt2 != null) {
					tt2.setOnFinished(null);
				}
                                flag = false;
                                  resetButton.setDisable(false);
                               stepButton.setDisable(false);
                                  isStepping = false;
                               sort.resume();
				break;
			case PAUSED:
				// Change the status
				setStatus(Status.PLAYING);
                                System.out.println(currentStatus.toString());
                                flag = true;
                                sort.resume();
                                  resetButton.setDisable(true);
                                stepButton.setDisable(true);
                                  isStepping = false;
				break;
		}
         
     
    }
    @FXML
    private void handleDataInput(ActionEvent eve){
     
        runButton.setDisable(true);
     for(int index = 0; index < textArray.size(); index++) 
          inputTextArray.get(index).setVisible(true);
        
     
    userInputButton.setVisible(true);
    }
    
    @FXML
    private void handleDataSetUp(ActionEvent a){
        runButton.setDisable(false);
        // get radio
       algorithmStr = (String) algorithmToggleGroup.getSelectedToggle().getUserData();
       // check if the binary search is selected
        if( (algorithmStr.equals("Binary Search"))|| (algorithmStr.equals("Sequenital Search"))){
              
           searchTarget = Integer.parseInt(inputText1.getText());
           userInputButton.setDisable(true);
          inputText1.setDisable(true);
        }
          
        
    
           // store user input data into the array that will be sorted
       for(int index = 0; index < textArray.size(); index++) 
           textArray.get(index).setText(inputTextArray.get(index).getText());
       
      // disable visibility for input text area
      for(int index = 0; index < textArray.size(); index++) 
              inputTextArray.get(index).setVisible(false);
     
      
            userInputButton.setVisible(false);
     
        
        nextSortIteration();
      
  //     handleRunAction(new ActionEvent());
        
    }
    
    @FXML
    private void reset(ActionEvent e){
        
        // start or restart the program
        System.out.println("thread is alive: "+sort.th.isAlive());
        if(sort.th.isAlive()){
          //  sort.suspend();
            
            sort.interruptThread();
            sort= new BackgroundSorting();
        }
        else{
         sort = new BackgroundSorting();
        }
        runButton.setDisable(false);
        for(int i = 0; i < recArray.size(); i++){
            recArray.get(i).setTranslateX(0);
        }
        recArray.clear();
        textArray.clear();
        topPane1.setTranslateX(0);
        topPane2.setTranslateX(0);
        bottomPane.setTranslateX(0);
        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        
        stepButton.setDisable(false);
//     sort.suspend();
        
//     topPane1.setVisible(false);
//     topPane2.setVisible(false);
//     bottomPane.setVisible(false);
     sort.interruptThread();
     inputButton.setDisable(false);
        start();
         speed = slider.getValue();
    }
    
  private void start(){
      
      
      sort.suspend();
      
     isStepping = false;
    
      
      
    runButton.setText("Sort");
  currentStatus = Status.STOPPED;

        
      Random rand = new Random();
       // set up arrays for values to get stored 
       textArray.add(text1);
       textArray.add(text2);
       textArray.add(text3);
       textArray.add(text4);
       textArray.add(text5);
       textArray.add(text6);
       textArray.add(text7);
       textArray.add(text8);
       textArray.add(text9);
       textArray.add(text10);
       
       // set up an array for user date input 
       inputTextArray.add(inputText1);
       inputTextArray.add(inputText2);
       inputTextArray.add(inputText3);
       inputTextArray.add(inputText4);
       inputTextArray.add(inputText5);
       inputTextArray.add(inputText6);
       inputTextArray.add(inputText7);
       inputTextArray.add(inputText8);
       inputTextArray.add(inputText9);
       inputTextArray.add(inputText10);
       
       
       recArray.add(rec1);
       recArray.add(rec2);
       recArray.add(rec3);
       recArray.add(rec4);
       recArray.add(rec5);
       recArray.add(rec6);
       recArray.add(rec7);
       recArray.add(rec8);
       recArray.add(rec9);
       recArray.add(rec10);
       // disable visibility for now
    

    
       label1.setVisible(false);
       label2.setVisible(false);
       label3.setVisible(false);
       
        for (Text textArray1 : textArray) {
            textArray1.setText(Integer.toString(rand.nextInt(100)));
        }


      drawArrow();
      topPane1.setVisible(false);
      topPane2.setVisible(false);
      bottomPane.setVisible(false);
     
      
      algorithmStr = (String) algorithmToggleGroup.getSelectedToggle().getUserData();
      
     
       
      //      sort.th.start();
        nextSortIteration();
        
        
    
  }
  
    
  private void nextSortIteration(){
    

       
//        if (!isStepping && currentStatus != Status.PLAYING) {
//			return;
//		}
//     if (tt2 != null && tt2.getStatus() == Animation.Status.RUNNING) {
//			return;
//		}


     
        		switch (algorithmStr) {
			case "Selection Sort":
                            currentAlgorithmLabel.setText("Selection Sort");
                               selectionSortText();
                               topPane1.setVisible(true);
                               topPane2.setVisible(true);
                               bottomPane.setVisible(true);
                               label1.setVisible(true);
                               label2.setVisible(true);
                               label3.setVisible(true);
                               label1.setText("Min");
                               label2.setText("Upper");
                               label3.setText("Current");
                         sortingVideo =  "<iframe width=\"560\" height=\"315\" src=\"http://www.youtube.com/embed/f8hXR_Hvybo\" frameborder=\"0\" allowfullscreen></iframe>";
                     
				break;
			case "Bubble Sort":
                            currentAlgorithmLabel.setText("Bubble Sort");
                                bubbleSortText();
                                label2.setVisible(true);
                                label3.setVisible(true);
                                topPane2.setVisible(true);
                                label2.setText("Upper");
                                bottomPane.setVisible(true);
                                label3.setText("Current");
                                sortingVideo =  "<iframe width=\"560\" height=\"315\" src=\"http://www.youtube.com/embed/8Kp-8OGwphY\" frameborder=\"0\" allowfullscreen></iframe>";
				 
                                break;
			case "Insertion Sort":
                                currentAlgorithmLabel.setText("Insertion Sort");
				insertionSortText();
                                label2.setVisible(true);
                                label3.setVisible(true);
                                topPane2.setVisible(true);
                                label2.setText("Insert");
                                bottomPane.setVisible(true);
                                label3.setText("Current");
                             sortingVideo =  "<iframe width=\"560\" height=\"315\" src=\"http://www.youtube.com/embed/DFG-XuyPYUQ\" frameborder=\"0\" allowfullscreen></iframe>";
                  //youtube.com/embed/DFG-XuyPYUQ
				break;
			case "Quick Sort":
                                currentAlgorithmLabel.setText("Quick Sort");
                                quickSortText();
                                topPane1.setVisible(true);  
				topPane2.setVisible(true);
                                bottomPane.setVisible(true);
                                label1.setVisible(true);
                               label2.setVisible(true);
                               label3.setVisible(true);
                               label1.setText("Lower");
                               label2.setText("Upper");
                               label3.setText("Pivot");
                               sortingVideo =  "<iframe width=\"560\" height=\"315\" src=\"http://www.youtube.com/embed/aQiWF4E8flQ\" frameborder=\"0\" allowfullscreen></iframe>";
				break;
			case "Binary Search":
                               currentAlgorithmLabel.setText("Binary Search");
                               binarySearchText();
                               topPane1.setVisible(true);
                               topPane2.setVisible(true);
                               bottomPane.setVisible(true);
                               label1.setVisible(true);
                               label2.setVisible(true);
                               label3.setVisible(true);
                               label1.setText("lower");
                               label2.setText("Upper");
                               label3.setText("mid");
                               searchInput.setDisable(false);
                               searchButton.setVisible(true);
                               //youtube.com/embed/D5SrAga1pno 
                               sortingVideo =  "<iframe width=\"560\" height=\"315\" src=\"http://www.youtube.com/embed/D5SrAga1pno\" frameborder=\"0\" allowfullscreen></iframe>";
				break;
                        case "Linear Search":
                               currentAlgorithmLabel.setText("Sequential Search");
                               bottomPane.setVisible(true);
                                label3.setVisible(true);
                                label3.setText("Current");
                                searchInput.setDisable(false);
                                 searchButton.setVisible(true);
                                 sequentialSearchText();
                             sortingVideo =  "<html><body><iframe width=\"560\" height=\"315\" src=\"http://www.youtube.com/embed/CX2CYIJLwfg\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
				break;
			
		}
                        
//                 webView = new WebView();
        webEngine = webView.getEngine();
        webEngine.loadContent(sortingVideo);
//         videoPane.getChildren().add(webView);      
                        
      
   isStepping = false;   
      
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
            sort = new BackgroundSorting();

    slider.setMin(200);
    slider.setMax(2000);
    slider.setValue(200);
    slider.setShowTickLabels(true);
    slider.setShowTickMarks(true);
    slider.setMajorTickUnit(100);
    slider.setMinorTickCount(50);
    slider.setBlockIncrement(100);
   
    
//          webView = new WebView();
//        webEngine = webView.getEngine();

    	sortIterationHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sort.resume();
			}
		};
    
    
    Platform.runLater(new Runnable() {
			@Override
			public void run() {
		start();
			}
    });
  
    isStepping = false;
 // start();
  
  
    } 
  
    private void drawArrow(){
        // size of panels are 57 by 94
            Path bottomArrow = new Path();           //40
         bottomArrow.getElements().add(new MoveTo(23.5, 70));
          bottomArrow.getElements().add(new LineTo(23.5, 10));//10
            bottomArrow.getElements().add(new LineTo(15.5, 20));//20
            bottomArrow.getElements().add(new LineTo(23.5, 10));//10
             bottomArrow.getElements().add(new LineTo(31.5, 20));//20
             bottomArrow.setStrokeWidth(1);
              bottomArrow.setStroke(Color.RED);
                 
                   
          Path topArrow1 = new Path();
          topArrow1.getElements().add(new MoveTo(26, 25));//15
          topArrow1.getElements().add(new LineTo(26, 80));
          topArrow1.getElements().add(new LineTo(18, 65));
          topArrow1.getElements().add(new LineTo(26, 80));
         topArrow1.getElements().add(new LineTo(34, 65));
         topArrow1.setStrokeWidth(1);
          topArrow1.setStroke(Color.BLACK);

          Path topArrow2 = new Path();
          topArrow2.getElements().add(new MoveTo(23.5, 15));
          topArrow2.getElements().add(new LineTo(23.5, 80));
          topArrow2.getElements().add(new LineTo(15.5, 60));
          topArrow2.getElements().add(new LineTo(23.5, 80));
         topArrow2.getElements().add(new LineTo(31.5, 60));
         topArrow2.setStrokeWidth(1);
          topArrow2.setStroke(Color.BLACK);
    
          //topPane1,topPane2
  bottomPane.getChildren().addAll(bottomArrow);
  topPane1.getChildren().add(topArrow1);
  topPane2.getChildren().add(topArrow2);
        
    }
    
    private void moveArrow(AnchorPane arrow, HBox currentRec){
        
           speed = slider.getValue();
         double temp1 = arrow.localToScene(arrow.getBoundsInLocal()).getMaxX(); 
                                                                    
         double temp2 = currentRec.localToScene(currentRec.getBoundsInLocal()).getMaxX() ;
         
         double position = temp2 -temp1;
         
        
                tt2 = new TranslateTransition();
        tt2.setNode(arrow);
       // tt2.setFromX(temp2);
        tt2.setToX(arrow.getTranslateX()+position);
  
       
        
          if (speed< 200) {
	tt2.setDuration(Duration.millis(200));
            } 
        
        else
            {
            tt2.setDuration(Duration.millis(speed));
            
            }
     
        tt2.setInterpolator(Interpolator.EASE_BOTH);
 
        
  //    if (!isStepping) {
				tt2.setOnFinished(sortIterationHandler);
//			}
         tt2.play();
         
    }
    
    
    private void swap(HBox first, HBox second, int rec1Index, int rec2Index){
        
        
                     Text temp =textArray.get(rec1Index);//.getText();
                            final HBox tempRec  =  recArray.get(rec1Index);
            
                              textArray.set(rec1Index, textArray.get(rec2Index));
                              textArray.set(rec2Index, temp);
                         recArray.set(rec1Index, recArray.get(rec2Index));
                          recArray.set(rec2Index, tempRec);
                       speed = slider.getValue();


//         double temp1 = first.localToScene(first.getBoundsInLocal()).getMaxX(); 
         double temp1 = first.boundsInParentProperty().get().getMaxX();
                                                                    
       //  double temp2 = second.localToScene(second.getBoundsInLocal()).getMaxX() ;
         double temp2 = second.boundsInParentProperty().get().getMaxX();
                                                                        
        
     
        double distance1 = temp2-temp1;
        double distance2 = temp1-temp2;

        tt1 = new TranslateTransition();
        tt1.setNode(first);
     
        tt1.setToX(first.getTranslateX() + distance1);
    
        
        if (speed< 200) {
	tt1.setDuration(Duration.millis(200));
	} 
        
        else
            {
            tt1.setDuration(Duration.millis(speed));
            
            }
     
        tt1.setInterpolator(Interpolator.EASE_BOTH);
        
        tt2 = new TranslateTransition();
        tt2.setNode(second);
  
        tt2.setToX(second.getTranslateX()+distance2);
  
       
        
          if (speed< 200) {
	tt2.setDuration(Duration.millis(200));
            } 
        
        else
            {
            tt2.setDuration(Duration.millis(speed));
            
            }
     
        tt2.setInterpolator(Interpolator.EASE_BOTH);

        
      if (!isStepping) {
		tt2.setOnFinished(sortIterationHandler);
			}
  
  
        tt1.play();
       tt2.play();

    }

    
    
    
    
    
 // Text   
    
  private void bubbleSortText(){
        
              myList = FXCollections.observableArrayList(  
        "1. Set the marker (upper) for the unsorted section at end of the list",
        "2. While the unsorted section has more than one element do steps 3-8",
        "3.     Set the current element marker (current) on second element of list",
        "4.     While current is not to the right of upper do steps 5-7",
        "5.         If the element at current is less than the element to its left then",
        "6.             Exchange these two elements",
        "7.         Move current to the right one position",
        "8.     Move upper to the left one position",
        "9. Stop"
        );
       
         list.setItems(myList);
    }
    
   private void selectionSortText(){
        myList = FXCollections.observableArrayList(  
       "1. Set the marker (upper) for the unsorted section at end of the list",
       "2. While the unsorted section has more than one element do steps 3-10",
       "3.      Set the marker (largest) for the largest so far at beginning of list",
       "4.      Set the current element marker (current) on second element of list",
       "5.      While current is not to the right of upper do steps 6-8",
       "6.          If the element at current is larger than the element at largest then",
       "7.              Set largest to current",
       "8.          Move current to the right one position",
       "9.      Exchange element at largest with element at upper",
      "10.      Move upper to the left one position",
       "11. Stop");
        
        list.setItems(myList);
       
   }
  
       
   private void quickSortText(){
        myList = FXCollections.observableArrayList( 
             
   "1. If the list to sort has more than 1 element then",
   "2.  If the list has exactly two elements then",
   "3.  If the two elements are out of order then",
   "4.  Exchange them",
   "        Else",
   "5.          Exchange the median of the first three elements with the first",
   "6.          Set the pivot marker (pivot) on the first of the elements",
   "7.          Set the lower marker (lower) on the second element",
   "8.          Set the upper marker (upper) on the last element",
   "9.          While lower is not to the right of upper do steps 10-15",
   "10.             While the element at lower is not larger than the element at pivot do step 11",
   "11.                 Move lower to the right one position",
   "12.             While the element at upper is larger than the element at pivot do step 13",
   "13.                 Move upper to the left one position",
   "14.             If lower is to the left of upper then",
   "15.         Exchange the elements at lower and upper",
   "16.         Exchange the elements at pivot and upper",
   "17.         Apply Quick Sort to the sublist of elements to the left of upper",
   "18.         Apply Quick Sort to the sublist of elements to the right of upper",
   "19. Stop"
       );
        
        list.setItems(myList);
       
   }
   
  private void binarySearchText(){
      
      myList = FXCollections.observableArrayList( 
       "1.  Get the target value",
       "2.  Set the value of beginning (begin) to 1",
       "3.  Set the value of end (end) to end position of list",
       "4.  Set found to false",
       "5.  While the target value is not found and begin is less than or equal to end do steps 6-12",
       "6.     Set the value of middle (mid) to the middle position between begin and end",
       "7.     If the target value is equal to the value at position mid then",
       "8.         Print position mid",
       "9.         Set found to true",
       "10.    Else if the target value precedes the value at mid then",
       "11.        Set end to mid - 1",
       "       Else",
       "12.        Set begin to mid + 1",
       "13. If the target value has not been found then",
       "14.    Output a message that the value was not found",
       "15. Stop"
              
      );
      
       list.setItems(myList);
      
  }
  
  private void insertionSortText(){
      
        myList = FXCollections.observableArrayList(  
       "1. Set the current element marker (current) on the second element of the list",
       "2. While current is not past the end of the list do steps 3-7",
        "3.    Set the insertion element marker (insert) on same element as current",
        "4.    While insert is not on the first element",
         "            and the element at insert is not larger than the element on its left do steps 5-6",
        "5.       Exchange the element at insert with the element to its left",
        "6.       Move insert to the left one position",
        "7.    Move current to the right one position",
         "8. Stop"
        );
       
         list.setItems(myList);
      
  }
   private void sequentialSearchText(){
       myList = FXCollections.observableArrayList(
       "1. Set the current element marker (current) on the first element of the list",
       "2. While current is not the past end of the list",
       "3.      if current equals target search",
       "4.           search target is found",
       "5        else",
       "6.          Move current to the right one position",
       "7. value was not found"
               
       );
       list.setItems(myList);
   }

   
   private void sort(){
       System.out.println("before sorting");
        for (Text textArray1 : textArray) {
            System.out.print(textArray1.getText() + " ");
        }

              
    int outter = textArray.size()-1;
     
             
        while(outter > 0){
           
            int j = 0;
            int inner = 1;
            int largest = Integer.parseInt(textArray.get(j).getText());
        
            while(inner <= outter){
               
                if(Integer.parseInt(textArray.get(inner).getText()) > largest){
                   
                   
                    largest = Integer.parseInt(textArray.get(inner).getText());
                    j = inner;
          
                }
                
                inner++;
 
            }

            //swap inner and outter
                System.out.println();
                  //updateTextLabel(j,outter);
                
                 String temp =textArray.get(j).getText();//.getText();
                      System.out.println("first: " + textArray.get(j).getText() + "  second: " + textArray.get(outter).getText());
                      textArray.get(j).setText(textArray.get(outter).getText());
                      textArray.get(outter).setText(temp);
                      System.out.println("first: " + textArray.get(j).getText() + "  second: " + textArray.get(outter).getText());
                       //  Text tempText =textArray.get(j);//.getText();

//                              textArray.set(j, textArray.get(outter));
  //                            textArray.set(outter, tempText);
                
                
                
          
            outter--;
                   
        } 
        
        System.out.println("after sorting");
        for (Text textArray1 : textArray) 
            System.out.print(textArray1.getText() + " ");

        sort.resume();
   }
   
   
   
   
   
    // background Thread
    class BackgroundSorting implements Runnable{
    public Thread th;
    private int num;
    private int index;
    private int iteration;
    private boolean currentState;
    private boolean suspendFlag;
        BackgroundSorting(){
        
       th = new Thread(this, "myThread");
      
       suspendFlag= true;
       th.setDaemon(true);
      th.start();
     

    }

    @Override
    public void run() {
            synchronized(this){  while(suspendFlag){
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
               }
            }
              update(0);
       
             System.out.println("suspendflag is: " + suspendFlag);
             try { 
          switch(algorithmStr){
              case "Bubble Sort": 
                        
            
                bubbleSort();
          
                   break;
                  
              case "Selection Sort":
                    System.out.println("selection sort entering");

                   
                    selectionSort();
                    break;
               
              case "Insertion Sort":

                  insertionSort();
                  break;
                  
              case "Quick Sort":
                  System.out.println("going to quick sort");
                   updateArrowPos(topPane1, 1);
                   
                  quickSort(textArray,0,textArray.size()-1);
                  break;
                  case "Binary Search":
                  System.out.println("going to Binary Search");
               
                 binarySearch();
                  break;
                   
                case "Linear Search":
                  System.out.println("going to linear search");
                  linearSearch();
                  break;
          }
              } 
          catch (InterruptedException ex) {
              
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        System.out.println("total iteration: " +   iteration);
        
        for(int j = 0; j<textArray.size();j++)
            System.out.print(textArray.get(j).getText() + " ");
        Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    resetButton.setDisable(false);
                    inputButton.setDisable(false);
                }
            
        }
        );
        
    //    interruptThread();
     //  th.interrupt();
    
    }
    private synchronized void suspend(){
                 suspendFlag = true;
               
             
    }
    private synchronized void resume(){
       
            suspendFlag= false;
            notify();
        
    }
   
    private void update(final int itemList){

           
               Platform.runLater(new Runnable(){

                   @Override
                   public void run() {        
   
                            list.requestFocus();
                            list.scrollTo(itemList);
             list.getSelectionModel().select(itemList);
                   }
                   
               });
               sleep();
       

    }
     
    private void sleep(){
        
            try {

               th.sleep((long)speed);
   
           } catch (InterruptedException ex) {
               Logger.getLogger(BackgroundSorting.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
     
    private boolean waitForResume() throws InterruptedException{
             if(flag){
                 
                 return flag;
             }
                 
            synchronized(this){
                while(!flag ){
                wait();
            }
        }
            return true;
    }
    // to do
    private void currentlyStepping() throws InterruptedException{
       
       synchronized(this){
           while(isStepping){
            wait();
           }
       }
        
    }
    
    private int bubbleSort() throws InterruptedException{

        iteration = 0;
        int outter =  textArray.size()-1;
        System.out.println("outter: " + outter);
         updateArrowPos(topPane2,outter);

         while((outter >= 0) && waitForResume()){
                int inner = 1;
          currentlyStepping();
           update(1);
      
           
           update(2);
       
           updateArrowPos(bottomPane,inner);
        
           while((inner <= outter) && waitForResume()){

                 currentlyStepping();
                updateArrowPos(bottomPane,inner);
               update(3);
         
                 update(4);
            
               if(Integer.parseInt(textArray.get(inner).getText()) < Integer.parseInt(textArray.get(inner-1).getText())){
              
                          update(5);
                      updateAndSwap(inner,inner-1);
                   
             
                          iteration++;
               }
               else{
                 update(6);
                
                       
               }
              
               inner++;

           }
           
           
          
               update(7);
                  
                updateArrowPos(topPane2,outter);
                outter--;
                
       }
         update(8);
               
               return iteration;
    }
    
    private void updateAndSwap(final int firstIndex, final int secondIndex){
                    suspend();
              Platform.runLater(new Runnable(){

                   @Override
                   public void run() {        
                   
                         swap(recArray.get(firstIndex), recArray.get(secondIndex),firstIndex, secondIndex);
                   //        System.out.println("first: " + textArray.get(firstIndex).getText() + "  second: " + textArray.get(secondIndex).getText());
  
                   }
    });
              
              try {
				synchronized (this) {
					while (suspendFlag) {
						wait();
					}
				}
			} catch (InterruptedException ex) {
				Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
			}
}
  
   private void updateArrowPos( AnchorPane arrowToMove,  int arrowDistination){
       Platform.runLater(new Runnable(){

           @Override
           public void run() {
               moveArrow(arrowToMove, recArray.get(arrowDistination));
           }

       
       });
        try {
				synchronized (this) {
					while (suspendFlag) {
						wait();
					}
				}
			} catch (InterruptedException ex) {
				Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
			}
             
   }
    
    private void selectionSort() throws InterruptedException{
            update(0);
           
        int outter = textArray.size()-1;
        // upper move to outter
          updateArrowPos(topPane2,outter);
        // list update to 1
           
        // list update to 2
             
        while((outter > 0) && waitForResume()){
             update(1);
            currentlyStepping();
            int j = 0;
            int inner = 1;
            int largest = Integer.parseInt(textArray.get(j).getText());
            //move large arrow to j
          
            //update list to 3
               update(1);
          
                 updateArrowPos(topPane1,j);
                 
            // move current arrow to inner
                    update(2);
               
               updateArrowPos(bottomPane,inner);
            // update list to 4
             
            while((inner <= outter) &&  waitForResume()){
                 currentlyStepping();
                 update(3);
               
             //   updateArrowPos(topPane2,outter);
                //update list to 5
                
                if(Integer.parseInt(textArray.get(inner).getText()) > largest){
                    // update list to 6
                     update(4);
                  
                    // move large arrow to inner
                     update(5);
                
                     updateArrowPos(topPane1,inner);
                    largest = Integer.parseInt(textArray.get(inner).getText());
                    j = inner;
                  //  updateArrowPos(topPane1,j);
                }
                
                // update list to 7
                 update(6);
               
                updateArrowPos(bottomPane,inner);
                inner++;
 
            }
            
            //update list to 8
             update(7);
            
            //swap inner and outter
               updateAndSwap(j,outter);
            // update list to 9
               update(8);
             
//              updateArrowPos(topPane2,outter);
            outter--;
            // move upper arrow to outter
          updateArrowPos(topPane2,outter);
            // update list to 1
             update(9);
           
            
        }
        // update list to 10
         update(10);
        
    }
    
   private void insertionSort() throws InterruptedException{
       int i = textArray.size()-1;
       int j = 1;
       // move current arrow to j
         updateArrowPos(bottomPane,j);
       //update list to 0
       update(0);
      
       while((j <= i) && waitForResume()){
           currentlyStepping();
           // update list to 1
           update(1);
           
           int k = j;
           // move insert arrow to k-1
           updateArrowPos(topPane2,k-1);
           //update list  to 2
           update(2);
           
           while((k > 0) && (Integer.parseInt(textArray.get(k).getText()) <= Integer.parseInt(textArray.get(k-1).getText())) && waitForResume()){
                currentlyStepping();
               //update  list to 4
               update(4);
            
               //swap k and k-1
               updateAndSwap(k,k-1);
               //update llist 5
               update(5);
            
             
               k--;
               //move insert arrow to k
               updateArrowPos(topPane2,k);
               // update list to 2
                update(2);
              
           }
           //update list to 6
            update(6);
            
               updateArrowPos(bottomPane,j);
           j++;
           //move current arrow to j
//           updateArrowPos(bottomPane,j);
           // update list to 1
              update(1);
              
       }
       // update list to 7
        update(7);
        
       
   }
    
   private void quickSort(ArrayList<Text> arrayOfText, int paramInt1, int paramInt2) throws InterruptedException{
       System.out.println("param2: " + paramInt2);
       int i = paramInt2-paramInt1 +1;
       update(0);
      
       if(i > 1){
       //    System.out.println("first if");
           if(i == 2){
         //      System.out.println("second if");
               update(1);
               
               if(Integer.parseInt(arrayOfText.get(paramInt1).getText()) > Integer.parseInt(arrayOfText.get(paramInt2).getText())){
                     update(2);
                 
                     updateAndSwap(paramInt1,paramInt2);
               } 
            }
       
       
       else{
           update(4);
           int j = paramInt2;
           int k = findMedianPost(arrayOfText, paramInt1,paramInt1+1, paramInt1+2);
             
             updateAndSwap(paramInt1,k);
             update(5);
            
             int m = Integer.parseInt(arrayOfText.get(paramInt1).getText());
               System.out.println("param1: " + textArray.get(paramInt1).getText() + "  m: " + m);
            k = paramInt1; 
            //move pivot arrow (k+1)
             updateArrowPos(bottomPane, k);
             paramInt1++;
             update(6);
           
             //move lower arrow to (paramInt+1)
             updateArrowPos(topPane1, paramInt1);
             update(7);
            
             //move upper arrow to (paramInt2+1)
             updateArrowPos(topPane2, paramInt2);
             update(8);
            
             while((paramInt1 < paramInt2) &&  waitForResume()){
                update(9);
              
                
                while((Integer.parseInt(arrayOfText.get(paramInt1).getText()) < m) &&  waitForResume()){
                    paramInt1++;
                    update(10);
                  
                    //move lower arrow to (paramInt1+1)
                    updateArrowPos(topPane1, paramInt1);
                    update(9);
                    
                }
                
                while((Integer.parseInt(arrayOfText.get(paramInt2).getText())> m) &&  waitForResume()){
                    paramInt2--;
                    update(12);
                   
                    //move upper arrow to (param2+1)
                     updateArrowPos(topPane2, paramInt2);
                    update(11);
                 
                }
                update(13);
              
                if(paramInt1 < paramInt2){
                    update(14);
                    updateAndSwap(paramInt1, paramInt2);
                }
                update(8);
               
             }
          
             update(15);
           
             updateAndSwap(k, paramInt2);
             update(16);
            
             //something about save arrows
             update(0);
           
               System.out.println("k: " + k + " param2: " + paramInt2);
            //   updateAndSwap( k, paramInt2 - 1);
             quickSort(arrayOfText, k, paramInt2-1);
             // something about restore arrows;
             update(0);
            
             System.out.println("j: " + j + " param2: " + paramInt2);
             quickSort(arrayOfText,paramInt2+1,j);
             //something about restore arrows
           }
       }
       update(18);
       
   }
   
   private int findMedianPost(ArrayList<Text> anArrayOfText, int param1, int param2, int param3){
       if(
               (Integer.parseInt(anArrayOfText.get(param1).getText()) <= Integer.parseInt(anArrayOfText.get(param2).getText())) && 
               (Integer.parseInt(anArrayOfText.get(param2).getText()) <= Integer.parseInt(anArrayOfText.get(param3).getText())) ||
               (Integer.parseInt(anArrayOfText.get(param3).getText()) <= Integer.parseInt(anArrayOfText.get(param2).getText())) &&
               (Integer.parseInt(anArrayOfText.get(param2).getText()) <= Integer.parseInt(anArrayOfText.get(param1).getText()))
         )
                    return param2;
       
        if(
               (Integer.parseInt(anArrayOfText.get(param2).getText()) <= Integer.parseInt(anArrayOfText.get(param1).getText())) && 
               (Integer.parseInt(anArrayOfText.get(param1).getText()) <= Integer.parseInt(anArrayOfText.get(param3).getText())) ||
               (Integer.parseInt(anArrayOfText.get(param3).getText()) <= Integer.parseInt(anArrayOfText.get(param1).getText())) &&
               (Integer.parseInt(anArrayOfText.get(param1).getText()) <= Integer.parseInt(anArrayOfText.get(param2).getText()))
         )
                    return param1;
       
       return param3;
               
   }
   
   public void interruptThread(){
         th.interrupt();
         
       
   }

   private void binarySearch() throws InterruptedException{

 
              Platform.runLater(new Runnable(){

                   @Override
                   public void run() {        
                   
                    sort();
                   }
    });
              
              try {
				synchronized (this) {
					while (suspendFlag) {
						wait();
					}
				}
			} catch (InterruptedException ex) {
				Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
			}
        
      
       int i = 0;
       int lower = 0;
       int upper = textArray.size()-1;
       boolean found = false;
       // if search target is empty reset
       
     try{  
      searchTarget = Integer.parseInt(searchInput.getText());
        updateLabel(searchResultLabel,"Searching: " +Integer.toString(searchTarget));
      
     }
     catch(NumberFormatException e){
         
         updateLabel(searchResultLabel,"Error in number format");
         return;
     }
          update(0);
          
          //move lower Arrow (lower+1)
      //    moveArrow(topPane1,recArray.get(lower+1));
          update(1);
       
          //move upper arrow (upper+1)
       //   moveArrow(topPane2, recArray.get(upper));
          update(2);
         
          update(3);
         
         
          while((lower <=upper)&&(!found) &&  waitForResume()){
              currentlyStepping();
              int mid = (lower + upper)/2;
        //      System.out.println("mid: " + mid);
              update(4);
            
              // move mid arrow (mid + 1)
              moveArrow(bottomPane,recArray.get(mid));
              update(5);
           
              
              if(Integer.parseInt(textArray.get(mid).getText()) == searchTarget){
                  update(6);
                
                  String str = Integer.toString(searchTarget)+" found at index "+Integer.toString(mid);
                  updateLabel(searchResultLabel, str);
                  found = true;
                  
              }
              else if( searchTarget < Integer.parseInt(textArray.get(mid).getText())){
                  update(8);
                     moveArrow(topPane2, recArray.get(upper));
                  upper = mid-1;
      //            System.out.println("upper: "+ upper);
                 // upper++;
                  update(9);
                
                  // move upper arrow to (upper+1)
//                  moveArrow(topPane2, recArray.get(upper));
              }
              else{
                  update(11);
                 moveArrow(topPane1,recArray.get(lower));
                  lower = mid+1;
                //  System.out.println("lower: " + lower);
                 // lower++;
                  // move lower arrow(lower+1)
            //      moveArrow(topPane1,recArray.get(lower));
              }
              update(3);
              
       }
           update(12);
           if(!found)
           {
               update(13);
              
               updateLabel(searchResultLabel, "The value was not found");
           }
           
           update(14);
   }
   
   private void updateLabel(final Label label, final String str){
       Platform.runLater(new Runnable(){

           @Override
           public void run() {
               System.out.println("inside updatelabel str: " + str);
               label.setText(str);
           }
           
       });
   }


   private void linearSearch() throws InterruptedException{
       boolean found = false;
       int index = 0;
        int target; 
    
      try{  
        target = Integer.parseInt(searchInput.getText());
         updateLabel(searchResultLabel,"Searching: " +Integer.toString(target));
      }
     catch(NumberFormatException e){
         updateLabel(searchResultLabel,"Error in number format");
         return;
     }
       
           
           
       System.out.println("target is: " + target);
       update(0);
      
       while((index < textArray.size()) && !found && waitForResume()){
           currentlyStepping();
           update(1);
         
           if(Integer.parseInt(textArray.get(index).getText()) == target){
               update(2);
           
               System.out.println("inside while target is: " + target);
               String targetStr = Integer.toString(target);
               System.out.println("inside while target is: " + Integer.toString(target));
                String resultStr  = Integer.toString(target) +  " found at index " + Integer.toString(index);
                System.out.println("result: " + resultStr);
                  updateLabel(searchResultLabel, resultStr);
                  found = true;
                  update(3);
               
           }
           else{
               // move arrow
               update(5);
             
           
       }
            updateArrowPos(bottomPane, index);
           index++;
           
       }
       if(!found){
            update(6);
             updateLabel(searchResultLabel, "The value was not found");
       }
     }
   
    }
 
 
    
}
