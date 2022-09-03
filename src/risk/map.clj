(ns risk.map (:require [clojure.set :refer :all]))

(def continents {:north-america #{:alaska :alberta :central-america :eastern-us :greenland :north-west-territory :ontario :quebec :western-us}
                 :europe #{:great-britain :iceland :northern-europe :scandinavia :southern-europe :ukraine :western-europe}
                 :asia #{:afghanistan :china :india :irkutsk :japan :kamchatka :middle-east :mongolia :siam :siberia :ural :yakutsk}
                 :south-america #{:argentina :brazil :peru :venezuela}
                 :africa #{:congo :east-africa :egypt :madagascar :north-africa :south-africa}
                 :australia #{:eastern-australia :indonesia :new-guinea :western-australia}})

(def neighbors #{#{:quebec :ontario} #{:afghanistan :india} #{:southern-europe :western-europe} #{:north-africa :east-africa}
                 #{:ukraine :afghanistan} #{:ural :ukraine} #{:mongolia :kamchatka} #{:irkutsk :mongolia}
                 #{:central-america :eastern-us} #{:madagascar :east-africa} #{:peru :venezuela} #{:great-britain :scandinavia}
                 #{:new-guinea :western-australia} #{:south-africa :madagascar} #{:north-africa :brazil}
                 #{:north-west-territory :greenland} #{:china :mongolia} #{:japan :kamchatka} #{:quebec :greenland}
                 #{:iceland :scandinavia} #{:ontario :western-us} #{:argentina :brazil} #{:middle-east :egypt}
                 #{:southern-europe :north-africa} #{:congo :east-africa} #{:siberia :yakutsk} #{:western-europe :northern-europe}
                 #{:china :india} #{:central-america :western-us} #{:north-west-territory :alberta} #{:ontario :greenland}
                 #{:china :siberia} #{:western-europe :great-britain} #{:southern-europe :ukraine} #{:central-america :venezuela}
                 #{:south-africa :east-africa} #{:new-guinea :indonesia} #{:ukraine :scandinavia} #{:iceland :greenland}
                 #{:kamchatka :alaska} #{:ukraine :northern-europe} #{:yakutsk :irkutsk} #{:siam :india} #{:middle-east :east-africa}
                 #{:southern-europe :northern-europe} #{:egypt :east-africa} #{:china :siam} #{:middle-east :afghanistan}
                 #{:northern-europe :scandinavia} #{:north-west-territory :ontario}
                 #{:eastern-us :western-us} #{:south-africa :congo} #{:western-us :alberta} #{:western-europe :north-africa}
                 #{:mongolia :japan} #{:venezuela :brazil} #{:argentina :peru} #{:western-australia :indonesia} #{:china :afghanistan}
                 #{:ural :afghanistan} #{:southern-europe :egypt} #{:yakutsk :kamchatka} #{:siberia :irkutsk} #{:siberia :ural}
                 #{:irkutsk :kamchatka} #{:middle-east :southern-europe} #{:siberia :mongolia} #{:great-britain :iceland}
                 #{:eastern-us :ontario} #{:north-africa :egypt} #{:alaska :alberta} #{:new-guinea :eastern-australia}
                 #{:siam :indonesia} #{:ontario :alberta} #{:western-australia :eastern-australia} #{:peru :brazil}
                 #{:congo :north-africa} #{:quebec :eastern-us} #{:great-britain :northern-europe} #{:china :ural}
                 #{:middle-east :ukraine} #{:middle-east :india} #{:north-west-territory :alaska}})

(defn neighbors-of [territory]
  (disj (reduce union #{} (filter territory neighbors)) territory))
