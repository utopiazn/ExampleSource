package dao;

import javax.sql.DataSource;
import logic.SaleLine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SaleLineDaoImpl implements SaleLineDao{
	
	private static final String INSERT = "INSERT INTO sale_line(sale_id, sale_line_id, item_id, quantity, update_time)"
			+ " VALUES(?, ?, ?, ?, ?)";

	private SimpleJdbcTemplate template;

	@Override
	public void create(SaleLine saleLine) {
		// TODO Auto-generated method stub
		this.template.update(SaleLineDaoImpl.INSERT, saleLine.getSale().getSaleId(), saleLine.getSaleLineId(), saleLine
				.getItem().getItemId(), saleLine.getQuantity(), saleLine.getUpdateTime());
		
	}

}
