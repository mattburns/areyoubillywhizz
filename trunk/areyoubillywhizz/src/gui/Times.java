package gui;
import com.cloudgarden.resource.SWTResourceManager;

import formatter.TimeFormatter;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;

import timer.Timer;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Times extends org.eclipse.swt.widgets.Composite {

	{
		//Register as a resource user - SWTResourceManager will
		//handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}

	private Composite composite2;
	private TableItem tableItem2;
	private Composite composite1;
	private SashForm sashForm1;
	private TableItem tableItem4;
	private TableItem tableItem3;
	private TableColumn tableColumn2;
	private TableColumn tableColumn1;
	private TableItem tableItem1;
	private Table table1;
	public Label CurrentTime;

	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void main(String[] args) {
		showGUI();
	}
		
	/**
	* Auto-generated method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		Times inst = new Times(shell, SWT.NULL);
		Point size = inst.getSize();
		shell.setLayout(new FillLayout());
		shell.layout();
		if(size.x == 0 && size.y == 0) {
			inst.pack();
			shell.pack();
		} else {
			Rectangle shellBounds = shell.computeTrim(0, 0, size.x, size.y);
			shell.setSize(shellBounds.width, shellBounds.height);
		}
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	public Times(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	private void initGUI() {
		try {
			{

			}
			FillLayout thisLayout = new FillLayout(org.eclipse.swt.SWT.VERTICAL);
			thisLayout.type = SWT.VERTICAL;
			this.setLayout(thisLayout);
			{
				composite2 = new Composite(this, SWT.NONE);
				FillLayout composite2Layout = new FillLayout(
					org.eclipse.swt.SWT.HORIZONTAL);
				composite2.setLayout(composite2Layout);
				{
					CurrentTime = new Label(composite2, SWT.VERTICAL);
					CurrentTime.setAlignment(SWT.CENTER);
					CurrentTime.setText("0:16.56");
					CurrentTime.setFont(SWTResourceManager.getFont("Trebuchet MS", 72, 1, false, false));
					CurrentTime.setBackground(SWTResourceManager.getColor(192, 192, 192));
				}
			}
			{
				sashForm1 = new SashForm(this, SWT.NONE);
				sashForm1.setSize(60, 30);
				{
					composite1 = new Composite(sashForm1, SWT.NONE);
					GridLayout composite1Layout = new GridLayout();
					composite1Layout.makeColumnsEqualWidth = true;
					composite1.setLayout(composite1Layout);
					composite1.setBounds(54, 58, 350, 119);
					{
						GridData table1LData = new GridData();
						table1LData.grabExcessVerticalSpace = true;
						table1LData.grabExcessHorizontalSpace = true;
						table1LData.horizontalSpan = 5;
						table1 = new Table(composite1, SWT.NONE);
						table1.setLayoutData(table1LData);
						{
							tableColumn1 = new TableColumn(table1, SWT.NONE);
							tableColumn1.setText("tableColumn1");
							tableColumn1.setWidth(60);
						}
						{
							tableItem1 = new TableItem(table1, SWT.NONE);
							tableItem1.setText("tableItem1");
						}
						{
							tableItem2 = new TableItem(table1, SWT.NONE);
							tableItem2.setText("tableItem2");
						}
						{
							tableColumn2 = new TableColumn(table1, SWT.NONE);
							tableColumn2.setText("tableColumn2");
							tableColumn2.setWidth(60);
						}
						{
							tableItem3 = new TableItem(table1, SWT.NONE);
							tableItem3.setText("tableItem3");
						}
						{
							tableItem4 = new TableItem(table1, SWT.NONE);
							tableItem4.setText("tableItem4");
						}
					}
				}
			}
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
