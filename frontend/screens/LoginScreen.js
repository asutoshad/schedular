import React, { useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet, TouchableOpacity, Alert, ActivityIndicator } from 'react-native';

export function LoginScreen({ navigation }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [loading, setLoading] = useState(false);

  const handleLogin = async () => {
    // Validate inputs
    if (!username.trim() || !password.trim()) {
      Alert.alert('Error', 'Please fill in all fields');
      return;
    }

    setLoading(true);

    try {
      const response = await fetch('http://localhost:8081/api/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username: username.trim(),
          password: password,
        }),
      });

      const data = await response.json();

      if (response.ok) {
        // Login successful
        Alert.alert(
          'Success',
          'Logged in successfully!',
          [
            {
              text: 'OK',
              onPress: () => {
                // Store the token if provided
                // await AsyncStorage.setItem('authToken', data.token);
                // await AsyncStorage.setItem('user', JSON.stringify(data.user));
                
                // Navigate to home screen
                // navigation.navigate('Home');
              },
            },
          ]
        );
      } else {
        // Login failed
        Alert.alert('Error', data.message || 'Invalid username or password');
      }
    } catch (error) {
      console.error('Login error:', error);
      Alert.alert(
        'Error',
        'Unable to connect to the server. Please check your connection and try again.'
      );
    } finally {
      setLoading(false);
    }
  };

  const handleGoogleLogin = () => {
    // Handle Google login logic
    Alert.alert('Info', 'Google login feature coming soon!');
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Login</Text>
      
      <TextInput
        placeholder="Username"
        style={styles.input}
        value={username}
        onChangeText={setUsername}
        editable={!loading}
        autoCapitalize="none"
        autoCorrect={false}
      />
      
      <TextInput
        placeholder="Password"
        style={styles.input}
        secureTextEntry
        value={password}
        onChangeText={setPassword}
        editable={!loading}
        autoCapitalize="none"
      />
      
      {loading ? (
        <ActivityIndicator size="large" color="#007AFF" style={styles.loader} />
      ) : (
        <Button title="Login" onPress={handleLogin} />
      )}
      
      <TouchableOpacity 
        onPress={handleGoogleLogin} 
        style={styles.googleButton}
        disabled={loading}
      >
        <Text style={styles.googleText}>Login with Google</Text>
      </TouchableOpacity>
      
      <TouchableOpacity 
        onPress={() => navigation.navigate('Signup')}
        style={styles.signupLink}
        disabled={loading}
      >
        <Text style={styles.signupText}>Don't have an account? Sign up</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    justifyContent: 'center',
    backgroundColor: '#fff',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    textAlign: 'center',
    marginBottom: 20,
  },
  input: {
    borderWidth: 1,
    borderColor: '#999',
    padding: 10,
    marginBottom: 10,
    borderRadius: 5,
    fontSize: 16,
  },
  loader: {
    marginVertical: 10,
  },
  googleButton: {
    marginTop: 20,
    padding: 10,
    backgroundColor: '#db4437',
    borderRadius: 5,
    alignItems: 'center',
  },
  googleText: {
    color: '#fff',
    fontWeight: 'bold',
    fontSize: 16,
  },
  signupLink: {
    marginTop: 15,
    alignItems: 'center',
  },
  signupText: {
    color: '#007AFF',
    fontSize: 14,
  },
});