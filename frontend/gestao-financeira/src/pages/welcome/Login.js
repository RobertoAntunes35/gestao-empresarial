import React from 'react';

import { Text, ScrollView, ImageBackground, Dimensions, StyleSheet } from 'react-native';

const LoginScreen = ({ navigation }) => {
    return (

        // Container Start

        <ScrollView style={styles.scrollView} showsVerticalScrollIndicator={false}>
            {/* BrandView */}
            <ImageBackground 
            source={require('./assets/background.png')}
            style={{height: Dimensions.get('window').height/2.5}}
            >
            </ImageBackground>
        </ScrollView>
    )
}

export default LoginScreen;

const styles = StyleSheet.create({
    
})