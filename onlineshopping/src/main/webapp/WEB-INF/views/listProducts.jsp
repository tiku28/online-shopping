<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">

	<div class="row">
		<!-- two columns -->
		
		<!-- first to display sidebar -->
		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp" %>			
		</div>
	
		<!-- second to display actual products -->
		
		<div class="col-md-9">
		
			<!-- Add breadcrumb component of bootstrap -->
			
			<div class="row"> 
			
				<div class="col-lg-12">
					
					<c:if test="${userClickAllProducts == true}">
						<script>
							window.categoryId = '';
						</script>
					<ol class="list-inline">
					
						<li><a href="${contextRoot}/home">Home </a></li>
						<li class="active"> > All Product</li>
					
					</ol>	
					
					</c:if>
					
					<c:if test="${userClickCategoryProducts == true}">
						<script>
							window.categoryId = '${category.id}';
						</script>
					<ul class="list-inline">
					
						<li><a href="${contextRoot}/home">Home </a></li>
						<li class="active">> Category ></li>
						<li class="active">${category.name}</li>
					
					</ul>	
					
					</c:if>
					
				</div>
			
			</div>
			
			<div class="row">
				
				<div class="col-xs-12">
				
				<table id="productListTable" class="table table-striped table-borderd">
				
					<thead>
						<tr>
							<th></th>
							<th>Name</th>
							<th>Band</th>
							<th>Price</th>
							<th>Ava. Quantity</th>
							<th></th>
						</tr>				
					</thead>
					
					<tfoot>
						<tr>
							<th></th>
							<th>Name</th>
							<th>Band</th>
							<th>Price</th>
							<th>Ava. Quantity</th>
							<th></th>
						</tr>				
					</tfoot>
				
				</table>
				
				</div>
			
			</div>
		</div>
		
	</div>
	
</div>