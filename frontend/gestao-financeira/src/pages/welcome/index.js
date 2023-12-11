import React from 'react';

import { 
    Text, 
    ScrollView, 
    ImageBackground, 
    Dimensions, 
    StyleSheet,
    TouchableOpacity, 
    View
} from 'react-native';



import { useNavigation } from '@react-navigation/native';

export default function Welcome() {
    const navigation = useNavigation()
    return (
        <View style={styles.container}>
            <ImageBackground
            source={{uri:'https://www.google.com/url?sa=i&url=https%3A%2F%2Fpt.pngtree.com%2Ffreepng%2Fabstract-geometry-shading-card_1723772.html&psig=AOvVaw0iXrCwk6t3hg-D4WYNSZwO&ust=1702051878073000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOCK17_b_YIDFQAAAAAdAAAAABAD'}} style={styles.imagemBackground}>
                <Text>Welcome</Text>
            </ImageBackground>
        </View>
    )
}

const styles = StyleSheet.create({

})