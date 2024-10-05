package Utility.Models.Ecommerce;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
public class CreateOrderModel {
	private List<OrderDetailModel> orders;

	@Data
	@Builder
	public static class OrderDetailModel{
		private String country;
		private String productOrderedId;
	}

}
