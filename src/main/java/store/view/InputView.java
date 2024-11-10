package store.view;

import store.view.dto.RequestOrder;
import store.view.dto.RequestOrderProduct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputView {


    private static final String ASK_PRODUCT_AND_QUANTITY = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";

    public RequestOrder readItem() {
        System.out.println(ASK_PRODUCT_AND_QUANTITY);
        String input = readLine();
        String fullPattern = "\\[([가-힣]+)-([0-9]+)\\](,\\[([가-힣]+)-([0-9]+)\\])*";
        String itemPattern = "\\[([가-힣]+)-([0-9]+)\\]";

        if (!input.matches(fullPattern)) {
            throw new IllegalArgumentException("잘못된 형식을 입력하셨습니다");
        }

        List<RequestOrderProduct> orderProducts = new ArrayList<>();
        Pattern p = Pattern.compile(itemPattern);
        Matcher m = p.matcher(input);

        while (m.find()) {
            String name = m.group(1);      // 상품명
            int quantity = Integer.parseInt(m.group(2));  // 수량
            orderProducts.add(new RequestOrderProduct(name, quantity));
        }
        return new RequestOrder(orderProducts);
    }


}
