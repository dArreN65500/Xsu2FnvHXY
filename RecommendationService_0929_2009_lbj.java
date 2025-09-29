// 代码生成时间: 2025-09-29 20:09:19
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

// 推荐的项目类
class RecommendationItem {
    private String id;
    private String name;
    private double score;

    public RecommendationItem(String id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }
}

// 推荐服务类
@Service
public class RecommendationService {

    private Map<String, List<RecommendationItem>> itemScores;
    private Map<String, List<String>> userItemInteractions;
    private final int topN = 5; // 推荐数量

    @Autowired
    public RecommendationService() {
        this.itemScores = new HashMap<>();
        this.userItemInteractions = new HashMap<>();
    }

    // 更新用户与项目的交互数据
    public void updateUserInteraction(String userId, String itemId) {
        userItemInteractions.computeIfAbsent(userId, k -> new ArrayList<>()).add(itemId);
    }

    // 计算项目的推荐分数
    public void calculateScores() {
        for (Map.Entry<String, List<String>> entry : userItemInteractions.entrySet()) {
            String userId = entry.getKey();
            List<String> interactions = entry.getValue();
            for (String itemId : interactions) {
                itemScores.computeIfAbsent(itemId, k -> new ArrayList<>()).add(new RecommendationItem(itemId, "Item_" + itemId, 0));
            }
        }
        itemScores.forEach((itemId, items) -> {
            double score = interactions.stream().mapToInt(user -> {
                return userItemInteractions.getOrDefault(user, Collections.emptyList()).stream()
                    .filter(itemId::equals).count();
            }).average().orElse(0);
            items.forEach(item -> item.setScore(score));
        });
        for (List<RecommendationItem> items : itemScores.values()) {
            Collections.sort(items, Comparator.comparingDouble(RecommendationItem::getScore).reversed());
        }
    }

    // 为用户推荐项目
    public List<RecommendationItem> recommendItems(String userId) {
        try {
            if (!userItemInteractions.containsKey(userId)) {
                throw new IllegalArgumentException("User not found");
            }
            calculateScores();
            List<String> interactedItems = userItemInteractions.get(userId);
            List<RecommendationItem> allItems = itemScores.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
            List<RecommendationItem> recommendations = allItems.stream()
                .filter(item -> !interactedItems.contains(item.getId()))
                .limit(topN)
                .collect(Collectors.toList());
            return recommendations;
        } catch (Exception e) {
            // 错误处理
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
