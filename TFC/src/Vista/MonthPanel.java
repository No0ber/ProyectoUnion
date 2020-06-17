package Vista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlador.Controlador;

public class MonthPanel extends JPanel {

	//private static final long   serialVersionUID    = 1L;
	
	protected int actualMonth;
	protected int year;
	protected ArrayList<Integer> dias, meses, anyos;
	protected ArrayList<String> fechas;
	
	protected String[] monthNames = {"Enero", "Febrero",
	"Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
	"Octubre", "Noviembre", "Diciembre"};
	
	protected String[] dayNames = {"D", "L", "M", "X", "J", "V", "S"};
	
	public MonthPanel(int month, int year) {
		this.actualMonth = month;
		this.year = year;
		
		dias = Controlador.getEventDias();
		meses = Controlador.getEventMeses();
		anyos = Controlador.getEventAnyos();
		
		fechas = Controlador.getArrayEventFechas();
		
		this.add(createGUI());
	}
	
	protected JPanel createGUI() {
		JPanel monthPanel = new JPanel(true);
		monthPanel.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption));
		monthPanel.setLayout(new BorderLayout());
		monthPanel.setBackground(Color.WHITE);
		monthPanel.setForeground(Color.BLACK);
		monthPanel.add(createTitleGUI(), BorderLayout.NORTH);
		monthPanel.add(createDaysGUI(), BorderLayout.SOUTH);
		
		return monthPanel;
	}
	
	protected JPanel createTitleGUI() {
		JPanel titlePanel = new JPanel(true);
		titlePanel.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption));
		titlePanel.setLayout(new FlowLayout());
		titlePanel.setBackground(Color.WHITE);
		
		JLabel label = new JLabel(monthNames[actualMonth] + " " + year);
		label.setForeground(SystemColor.activeCaption);
		
		titlePanel.add(label, BorderLayout.CENTER);
		
		return titlePanel;
	}
	
	protected JPanel createDaysGUI() {
		JPanel dayPanel = new JPanel(true);
		dayPanel.setLayout(new GridLayout(0, dayNames.length));
		
		Calendar today = Calendar.getInstance();
		int tMonth = today.get(Calendar.MONTH);
		int tYear = today.get(Calendar.YEAR);
		int tDay = today.get(Calendar.DAY_OF_MONTH);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, actualMonth);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		Calendar iterator = (Calendar) calendar.clone();
		iterator.add(Calendar.DAY_OF_MONTH, -(iterator.get(Calendar.DAY_OF_WEEK) - 1));
		
		Calendar maximum = (Calendar) calendar.clone();
		maximum.add(Calendar.MONTH, 1);
		
		for(int i = 0; i < dayNames.length; i++) {
			JPanel dPanel = new JPanel(true);
			dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			JLabel dLabel = new JLabel(dayNames[i]);
			dPanel.add(dLabel);
			dayPanel.add(dPanel);
		}
		
		int count = 0;
		int limit = dayNames.length * 6;
		
		while(iterator.getTimeInMillis() < maximum.getTimeInMillis()) {
			int lMonth = iterator.get(Calendar.MONTH);
			int lYear = iterator.get(Calendar.YEAR);
			
			JPanel dPanel = new JPanel(true);
			dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			JLabel dayLabel = new JLabel();
			
			//----------------------------------------------------------------------------------------------------
			if((lMonth == actualMonth) && (lYear == year))  {
				int lDay = iterator.get(Calendar.DAY_OF_MONTH);
				dayLabel.setText(Integer.toString(lDay));

				for(int k=0;k<fechas.size();k++) {
					//System.out.println(k);
					System.out.println("Dia "+lDay+" "+dias.get(k));
					System.out.println("Mes "+tMonth+" "+(meses.get(k)-1));
					System.out.println("Año "+tYear+" "+anyos.get(k));
					
					if((tMonth == meses.get(k)-1) && (tYear == anyos.get(k)) && (lDay == dias.get(k))) {
						System.out.println("If");
						dPanel.setBackground(Color.ORANGE);
					} else {
						System.out.println("else");
						//dPanel.setBackground(Color.WHITE);
					}
				}
			} else {
				dayLabel.setText(" ");
				dPanel.setBackground(Color.WHITE);
			}
			
			//----------------------------------------------------------------------------------------------------
			/*if((lMonth == actualMonth) && (lYear == year)) {
				int lDay = iterator.get(Calendar.DAY_OF_MONTH);
				dayLabel.setText(Integer.toString(lDay));
				
				if((tMonth == actualMonth) && (tYear == year) && (tDay == lDay)) {
					dPanel.setBackground(Color.ORANGE);
				} else {
					dPanel.setBackground(Color.WHITE);
				}
			} else {
				dayLabel.setText(" ");
				dPanel.setBackground(Color.WHITE);
			}*/
			//----------------------------------------------------------------------------------------------------
			
			dPanel.add(dayLabel);
			dayPanel.add(dPanel);
			iterator.add(Calendar.DAY_OF_YEAR, +1);
			count++;
			
			System.out.println("While.");
		}
		
		for(int i = count; i < limit; i++) {
			JPanel dPanel = new JPanel(true);
			dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			JLabel dayLabel = new JLabel();
			dayLabel.setText(" ");
			dPanel.setBackground(Color.WHITE);
			dPanel.add(dayLabel);
			dayPanel.add(dPanel);
		}
	
		return dayPanel;
	}
}