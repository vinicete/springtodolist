import { NextResponse } from 'next/server';

export async function POST(request: Request) {
  try {
    const body = await request.json();
    console.log('Register request body:', body);

    const response = await fetch('http://localhost:8080/api/login/signup', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(body),
    });

    console.log('Backend response status:', response.status);
    
    if (!response.ok) {
      const errorData = await response.text();
      console.error('Backend error response:', errorData);
      return NextResponse.json(
        { message: 'Registration failed', details: errorData },
        { status: response.status }
      );
    }

    const data = await response.json();
    console.log('Backend success response:', data);
    return NextResponse.json(data, { status: response.status });
  } catch (error) {
    console.error('Register API error:', error);
    return NextResponse.json(
      { message: 'Internal server error', details: error instanceof Error ? error.message : 'Unknown error' },
      { status: 500 }
    );
  }
} 