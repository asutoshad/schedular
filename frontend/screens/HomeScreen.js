import React from 'react';
import { View, Text, Button, StyleSheet } from 'react-native';

export function HomeScreen({ navigation }) {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Welcome to Schedular</Text>
      <Button title="Signup" onPress={() => navigation.navigate('Signup')} />
      <View style={{ marginTop: 10 }} />
      <Button title="Login" onPress={() => navigation.navigate('Login')} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20,
  },
  title: {
    fontSize: 24,
    marginBottom: 20,
  },
});
