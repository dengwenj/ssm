import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class KB {
    private final Map<String, List<ProdOrder>> map = new HashMap<>();
    private final List<ProdOrder> feList = new ArrayList<>();

    public KB() {
        initProdOrder();
        initFEData();
        List<ProdOrder> prodOrders = serviceHandler(feList);
        for (ProdOrder prodOrder : prodOrders) {
            System.out.println("insert 的数据：" + prodOrder);
        }
    }

    /**
     * =====================逻辑处理====================
     *
     * @param list 前端传递的数据
     * @return 返回整理好的数据添加到数据库
     */
    private List<ProdOrder> serviceHandler(List<ProdOrder> list) {
        List<ProdOrder> insertList = new ArrayList<>();

        // 拖动的到单元格的那一缸
        ProdOrder prodOrder = list.get(0);
        insertList.add(prodOrder);
        String[] daySortSplit = prodOrder.getDaySort().split("-");
        // 说明是次缸
        if (daySortSplit.length >= 2) {
            System.out.println("-----------日内排序数据有问题，按照业务自己处理-----------");
            return insertList;
        }

        int numDaySort = Integer.parseInt(daySortSplit[0]);
        String queryByData = prodOrder.getDate();
        // 日内排序大于等于2 要求进缸日从下一天开始
        if (numDaySort >= 2) {
            LocalDate localDate = LocalDate.parse(queryByData);
            LocalDate dateAddOne = localDate.plusDays(1);
            queryByData = dateAddOne.toString();
        }

        List<ProdOrder> continuousList = list.subList(1, list.size());
        String machine = prodOrder.getMachine();
        int i = 0;
        while (true) {
            // 模拟操作数据库
            List<ProdOrder> dbList = getProdOrderByMachineAndDate(machine, queryByData);
            // 得到所有的日内排序
            List<String> daySortList = dbList.stream().map(ProdOrder::getDaySort).collect(Collectors.toList());
            boolean one = daySortList.contains("1");
            boolean two = daySortList.contains("2");
            if (!one && !queryByData.equals(prodOrder.getDate())) {
                ProdOrder data = buildData(continuousList.get(i), machine, queryByData, "1");
                insertList.add(data);
                i++;
                if (i == continuousList.size()) {
                    break;
                }
            }
            if (!two) {
                ProdOrder data = buildData(continuousList.get(i), machine, queryByData, "2");
                insertList.add(data);
                i++;
                if (i == continuousList.size()) {
                    break;
                }
            }
            queryByData = LocalDate.parse(queryByData).plusDays(1).toString();
        }
        return insertList;
    }

    private ProdOrder buildData(ProdOrder prodOrder, String machine, String date, String daySort) {
        prodOrder.setMachine(machine);
        prodOrder.setDate(date);
        prodOrder.setDaySort(daySort);
        return prodOrder;
    }

    private List<ProdOrder> getProdOrderByMachineAndDate(String machine, String date) {
        List<ProdOrder> prodOrders = map.get(date);
        if (prodOrders == null) {
            return new ArrayList<>();
        }
        return prodOrders.stream()
            .filter((item) -> item.getMachine().equals(machine))
            .collect(Collectors.toList());
    }

    /**
     * 前端传递过来的数据
     */
    private void initFEData() {
        // 拖动到当前单元格的数据
        feList.add(new ProdOrder("1080B", "2024-12-18", "1"));
        // 连续排缸的数据, 即连续排 10 缸
        feList.add(new ProdOrder());
        feList.add(new ProdOrder());
        feList.add(new ProdOrder());
        feList.add(new ProdOrder());
        feList.add(new ProdOrder());
        feList.add(new ProdOrder());
        feList.add(new ProdOrder());
        feList.add(new ProdOrder());
        feList.add(new ProdOrder());
        feList.add(new ProdOrder());
    }

    /**
     * 模拟数据库的数据
     */
    private void initProdOrder() {
        List<ProdOrder> list1 = new ArrayList<>();
        list1.add(new ProdOrder("1080A", "2024-12-18", "1"));
        list1.add(new ProdOrder("1080B", "2024-12-18", "2"));
        list1.add(new ProdOrder("1080B", "2024-12-18", "3"));
        map.put("2024-12-18", list1);

        List<ProdOrder> list2 = new ArrayList<>();
        list2.add(new ProdOrder("1080B", "2024-12-19", "1"));
        map.put("2024-12-19", list2);

        List<ProdOrder> list3 = new ArrayList<>();
        list3.add(new ProdOrder("1080A", "2024-12-20", "2"));
        map.put("2024-12-20", list3);

        List<ProdOrder> list4 = new ArrayList<>();
        list4.add(new ProdOrder("1080A", "2024-12-21", "1"));
        list4.add(new ProdOrder("1080A", "2024-12-21", "2"));
        map.put("2024-12-21", list4);

        map.put("2024-12-22", null);

        List<ProdOrder> list5 = new ArrayList<>();
        list5.add(new ProdOrder("1080C", "2024-12-23", "1"));
        list5.add(new ProdOrder("1080A", "2024-12-23", "4"));
        map.put("2024-12-23", list5);
    }
}
