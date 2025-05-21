import org.example.DB_Util;
import org.example.Product;
import org.example.ProductImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


class ProductImplTest {

    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private Statement mockStatement;
    private ResultSet mockResultSet;
    private ProductImpl productImpl;

    @BeforeEach
    void setUp() {
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

        DB_Util.setTestConnection(mockConnection);

        productImpl = new ProductImpl();
    }

    @Test
    void create_test() throws Exception {
        //Given
        Product product = new Product(null, "TestProduct", 10.0, 1);
        String sql = "INSERT INTO products(name, price, quantity) VALUES (?, ?, ?)";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);

        //When
        productImpl.create(product);

        //Then
        verify(mockPreparedStatement).setString(1, "TestProduct");
        verify(mockPreparedStatement).setDouble(2, 10.0);
        verify(mockPreparedStatement).setInt(3, 1);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void getAll_test() throws Exception {
        //Given
        String sql = "SELECT * FROM products";
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(sql)).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getLong("id")).thenReturn(1L);
        when(mockResultSet.getString("name")).thenReturn("TestProduct");
        when(mockResultSet.getDouble("price")).thenReturn(50.5);
        when(mockResultSet.getInt("quantity")).thenReturn(5);

        //When
        List<Product> products = productImpl.getAll();

        //Then
        assertEquals(1, products.size());

        Product p = products.getFirst();
        assertEquals(1L, p.getId());
        assertEquals("TestProduct", p.getName());
        assertEquals(50.5, p.getPrice());
        assertEquals(5, p.getQuantity());
    }

    @Test
    void update_test() throws Exception {
        //Given
        Product product = new Product(1L, "UpdateProduct", 120.0, 15);
        String sql = "UPDATE products SET name=?, price=?, quantity=? WHERE id=?";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);

        //When
        productImpl.update(product);

        //Then
        verify(mockPreparedStatement).setString(1, "UpdateProduct");
        verify(mockPreparedStatement).setDouble(2, 120.0);
        verify(mockPreparedStatement).setInt(3, 15);
        verify(mockPreparedStatement).setLong(4, 1L);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void delete_test() throws Exception {
        //Given
        String sql = "DELETE FROM products WHERE id=?";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);

        //When
        productImpl.delete(1L);

        //Then
        verify(mockPreparedStatement).setLong(1, 1L);
        verify(mockPreparedStatement).executeUpdate();
    }
}