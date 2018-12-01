package view;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

public class MainWindowController implements Initializable {
	
	char [][] pipeData = {
			{'s', '-', '-', '-', '7', '7', '7', '7' , '7'},
			{'7', '7', '7', '7', '7', '7', '7', '7' , '7'},
			{'7', '7', '7', '7', '7', '7', '7', '7' , '7'},
			{'7', '7', '7', '7', '|', '7', '7', '7' , '7'},
			{'7', '7', '7', '7', 'L', '7', '7', '7' , '7'},
			{'7', '7', '7', '7', '7', '7', '7', '7' , '7'},
			{'7', '7', '7', '7', '|', '7', '7', '7' , '7'},
			{'7', '7', '-', '-', '-', '-', '-', '-' , 'g'},
	};
	
	@FXML
	PipeDisplayer pipeDisplayer;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pipeDisplayer.setPipeData(pipeData);
	}
	
	public void start() {
		System.out.println("Start.");
	}
	
	public void stop() {
		System.out.println("Stop.");
	}
	
	public void exit() {
		System.out.println("Exiting..");
		System.exit(0);
	}
	
	public void openFile() {
		System.out.println("Open File.");
		FileChooser fc = new FileChooser();
		fc.setTitle("Open Pipe File");
		fc.setInitialDirectory(new File("./resources"));
		
		FileChooser.ExtensionFilter txtExtensionFilter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
	    fc.getExtensionFilters().add(txtExtensionFilter);
	    fc.setSelectedExtensionFilter(txtExtensionFilter);
		File choosen = fc.showOpenDialog(null);

		if (choosen != null) {
			System.out.println(choosen.getName());

			List<char[]> lines = new ArrayList<char[]>();
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(choosen));

				String line;
				while ((line = reader.readLine()) != null) {
				    lines.add(line.toCharArray());
				}
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			char[][] charArray = lines.toArray(new char[lines.size()][]);
			pipeDisplayer.setPipeData(charArray);
		}
	}
}
