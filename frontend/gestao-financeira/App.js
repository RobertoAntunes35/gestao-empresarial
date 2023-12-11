import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import Welcome from './src/pages/welcome';
import { NavigationContainer } from '@react-navigation/native';

export default function App() {
  return (
    <NavigationContainer style={styles.container}>
      <StatusBar backgroundColor='#38A69D' barStyle='light-content'/>
      <Welcome/>
      <Text>HELLO</Text>
    </NavigationContainer>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
