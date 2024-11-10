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
    private static final String ASK_MEMBERSHIP_DISCOUNT_MESSAGE = "멤버십 할인을 받으시겠습니까? (Y/N)";
    private static final String ASK_ADDITIONAL_PURCHASE_MESSAGE = "감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)";
    private static final String ASK_FREE_ITEM_ADDITION_MESSAGE = "현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";
    private static final String ASK_PRODUCT_EXCLUSION_CONFIRMATION_MESSAGE = "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)";

    public RequestOrder askItem() {
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

    public String askMemberShipDiscount() {
        System.out.println(ASK_MEMBERSHIP_DISCOUNT_MESSAGE);
        return readLine();
    }

    public String askAdditionalPurchase() {
        System.out.println(ASK_ADDITIONAL_PURCHASE_MESSAGE);
        return readLine();
    }

    public String askFreeItemAddition() {
        System.out.println(ASK_FREE_ITEM_ADDITION_MESSAGE);
        return readLine();
    }

    public String askProductExclusion() {
        System.out.println(ASK_PRODUCT_EXCLUSION_CONFIRMATION_MESSAGE);
        return readLine();
    }

}
