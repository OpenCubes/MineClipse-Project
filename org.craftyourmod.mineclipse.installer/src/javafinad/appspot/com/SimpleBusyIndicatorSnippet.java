package javafinad.appspot.com;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.progress.UIJob;

public class SimpleBusyIndicatorSnippet {

	public SimpleBusyIndicatorSnippet() {
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = new Display ();
		
	
	

	Shell shell = new Shell(display);
	shell.setText("Helloworld");
	shell.open ();
	BusyIndicator.showWhile(display, new Runnable() {
		
		public void run() {
			
			System.out.println("The Cursor will change to busy indicator.");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("The Cursor change to normal state.");
		}
	}); 
	while (!shell.isDisposed ()) {
		if (!display.readAndDispatch ()) display.sleep ();
	}
	
	}

}
