package dd.ui;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import java.util.Vector;

public class NodeListTableModel extends AbstractTableModel //implements TableModelListener
{
    final String[] columnNames = {
        "ID",
        "Location"
    };
    
    Vector<Vector<Object>> data;
    
    public NodeListTableModel()
    {
      data = new Vector<Vector<Object>>();
      
      int n = 100;
      for (int i = 0; i < n;i++)
      {
        Vector<Object> temp = new Vector<Object>();
        temp.add(new Integer(i + 1));
        temp.add("Some location");
        data.add(temp);
      }
    }

    @Override
    public int getColumnCount()
    {
      // TODO Auto-generated method stub
      return columnNames.length;
    }
    
    @Override
    public String getColumnName(int col)
    {
      return columnNames[col];
    }

    @Override
    public int getRowCount()
    {
      return data.size();
    }

    @Override
    public Object getValueAt(int row, int col)
    {
      return data.get(row).get(col);
    }
    
    @Override
    public Class getColumnClass(int columnIndex)
    {
      return getValueAt(0, columnIndex).getClass();
    }
        
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
      
    // TODO Add the code to update the various values.
    super.setValueAt(aValue, rowIndex, columnIndex);
    }
}
